/**
 * 
 */
package com.jittr.android;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import com.jittr.android.api.betsquared.BSClientAPIImpl;
import com.jittr.android.bs.dto.BSUserBankStatement;
import com.jittr.android.util.Consts;
import com.paypal.android.MEP.CheckoutButton;
import com.paypal.android.MEP.MEPAddress;
import com.paypal.android.MEP.MEPAmounts;
import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalActivity;
import com.paypal.android.MEP.PayPalPayment;
import com.paypal.android.MEP.PaymentAdjuster;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * @author juliomiyares
 *
 */
public class GameOnManageBankActivity extends GameOnBaseActivity implements OnClickListener, OnItemSelectedListener{

    final static public int PAYPAL_BUTTON_ID = 10001;
	private final static int MAIN_PAGE = 1;
	private final static int REVIEW_PAGE = 2;
	private final static int STATUS_PAGE = 3;
	private final static int CANCELED_PAGE = 4;
	private final static int  FAILURE_PAGE = 5;
	
	private int inPage;  //set to what page we are currently in
	private Spinner paymentChoicesSpinner;
	private boolean _launchedPayment = false;
	private boolean _paypalLibraryInit = false;
	private GameOnProperties gameOnProperties;
	
	//Local references to our amounts and description
	private int duckettsBankBalance;
	private int duckettsInPlay;
	private int duckettsToPurchase;
	private double duckettsToPurchaseInDollars;
	private double pricePerDuckett = .0002; //from preferences or Global Consts or default properties
	private double taxRate = .04;   //from preferences or Global consts or default properties
	private double taxAmount;
	private double totalAmount;
	private EditText addDuckettBalanceEditText;

	//Static instance of PayPal
	private static PayPal ppObj;
	
	//Lets us keep a reference to the "Pay with PayPal" button
	private CheckoutButton _launchPayPalButton;
	
	//Reference to our number formatter (used for to format the amounts)
	private NumberFormat _df;
	
	//Keeps a reference to the progress dialog 
	private ProgressDialog _progressDialog;

	//reference to webservice interface
	BSClientAPIImpl bsAPI;
	BSUserBankStatement bankStatement;
	//The runnables are declared so we can make sure they are run on whatever thread we want
	
	//This lets us show the PayPal Button after the library has been initialized
    final Runnable showPayPalButtonRunnable = new Runnable() {
        public void run() {
            showPayPalButton();
        }
    };
    
    //This lets us run a loop to check the status of the PayPal Library init
    final Runnable checkforPayPalInitRunnable = new Runnable() {
        public void run() {
            checkForPayPalLibraryInit();
        }
    };
	private Button reviewOrderButton;

	public double getTaxAmount() {
		return taxAmount;
	}
	public double getDuckettsToPurchaseInDollars() {
		return duckettsToPurchaseInDollars;
	}
	
	/* set the ducketts to purchase property*/
	public void setDuckettsToPurchase(String editText) {
        duckettsToPurchase = 0;
        
		if (null != editText && !"".equals(editText.trim())) {
		   try {
			   duckettsToPurchase = Integer.parseInt(editText);
		   } catch (NumberFormatException e) {
			   duckettsToPurchase = 0;    
		   } finally {
			   reviewOrderButton.setEnabled((duckettsToPurchase > 0 ? true : false));			   
		   }
	   }  //if
       reviewOrderButton.setEnabled((duckettsToPurchase > 0 ? true : false));
       duckettsToPurchaseInDollars = duckettsToPurchase * pricePerDuckett;
       taxAmount = duckettsToPurchaseInDollars * taxRate;
       totalAmount = taxAmount + duckettsToPurchaseInDollars;
	} //setAddDuckettsToBalance
	/**
	 * 
	 */
	public GameOnManageBankActivity() {
		// TODO Auto-generated constructor stub
	}

	/* Load Main Page - no bottomBar on this page or on any other ecommerce transaction related page
	 * (non-Javadoc)
	 * @see com.jittr.android.GameOnBaseActivity#onCreate(android.os.Bundle)
	 */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameOnProperties = getAppContext().getGameOnProperties();
        //Set up our DecimalFormatter for our amounts
    	_df = (DecimalFormat)DecimalFormat.getCurrencyInstance(Locale.ENGLISH);
    	_df.setCurrency(Currency.getInstance("USD"));
        //get current host based BankStatement for the user
    	bsAPI = new BSClientAPIImpl();
    	HashMap<String,String>params = new HashMap<String,String>();
    	params.put("userid", Integer.toString(getAppContext().getLoginID()));
    	bankStatement = bsAPI.getUserBankBalance(params);
    	if (bankStatement.getStatus_code().equals("200")) {
    		duckettsInPlay = bankStatement.getDuckettsInPlay();
    		duckettsBankBalance = bankStatement.getDuckettsBankBalance();
    	} //if
        loadMainPage();
//        setBottomBar(0);
    }   //onCreate

    /* return true if edits pass and purchase button can be enabled
     * else false
     */
    private boolean passEdits() {
        String test  = addDuckettBalanceEditText.toString();
        if (null!= test && !"".equals(test.trim())) {
            try {
        	   duckettsToPurchase= Integer.parseInt(test);
            } catch (NumberFormatException e) {
               return false;	
            }
        	return true;
        }
    	return false;
    } //passEdits
    /* Load Main Page */
    protected void loadMainPage() {
    	super.setUpViews(Consts.LAYOUT_ADD_DONE);
        setContentView(R.layout.gameonmanagebank);
        inPage = MAIN_PAGE;
        startPayPalInit();
        ((TextView)findViewById(R.id.currentDuckettBalanceTextView)).setText(String.valueOf(duckettsBankBalance));
        ((TextView)findViewById(R.id.currentInPlayDuckettBalanceTextView)).setText(String.valueOf(duckettsInPlay));
      	addDuckettBalanceEditText = (EditText)findViewById(R.id.addDuckettBalanceEditText);
      	addDuckettBalanceEditText.setText(Integer.toString(duckettsToPurchase));
        addDuckettBalanceEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
                setDuckettsToPurchase(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
			}
        });

      	paymentChoicesSpinner = (Spinner)findViewById(R.id.paymentChoicesSpinner);
    	paymentChoicesSpinner.setOnItemSelectedListener(this);
        reviewOrderButton = (Button)findViewById(R.id.reviewOrderButton);
       	reviewOrderButton.setOnClickListener(this);
      	if (duckettsToPurchase > 0) reviewOrderButton.setEnabled(true);
   //     reviewOrderButton.setEnabled(passEdits());
    	if (null != getDoneButton()) getDoneButton().setOnClickListener(this); 
 
     } //loadMainPage

    /* Load Review Purchase Transaction Page - will initialize the paypal library and also position the 
     * paypal button on the bottom
     */
	private void loadReviewPage() {
	    	setContentView(R.layout.gameonreviewpurchase);
	    	inPage=REVIEW_PAGE;
	    	//Do all our setup of the Review page
            _df.setParseIntegerOnly(true);
            _df.setMaximumFractionDigits(0);
            String duckettsToPurchaseStr = _df.format(duckettsToPurchase);
            duckettsToPurchaseStr = duckettsToPurchaseStr.replace("$","");
	    	((TextView)findViewById(R.id.duckettsToPurchaseTextView)).setText("Duckets to Purchase - " + duckettsToPurchaseStr);
            _df.setMaximumFractionDigits(5);
	    	((TextView)findViewById(R.id.pricePerDuckettTextView)).setText("Price Per Duckett = " + _df.format( pricePerDuckett));
            _df.setMaximumFractionDigits(2);
           ((TextView)findViewById(R.id.subTotalTextView)).setText("Subtotal " + _df.format( duckettsToPurchaseInDollars));
            ((TextView)findViewById(R.id.taxAmountTextView)).setText("Tax Amount " + _df.format(taxAmount));
            ((TextView)findViewById(R.id.totalPurchaseTextView)).setText("Total Amount = "+ _df.format(totalAmount));

	    	//Do all our setup of the Review page
	    	//Check if the PayPal Library has been initialized yet. If it has, show the "Pay with PayPal button"
	    	//If not, show a progress indicator and start a loop that keeps checking the init status
	    	if (_paypalLibraryInit) {
	    		if (null == _launchPayPalButton.getParent()) {
	    		   ((RelativeLayout) findViewById(R.id.reviewPurchaseRelativeLayout)).addView(_launchPayPalButton);    		
	       		   ((RelativeLayout) findViewById(R.id.reviewPurchaseRelativeLayout)).setGravity(Gravity.CENTER_HORIZONTAL);
	    		} //if
	    	}
	    	else {
	        	//Display a progress dialog to the user and start checking for when the initialization is completed
	        	_progressDialog = new ProgressDialog( this );
	        	_progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        	_progressDialog.setMessage("Loading PayPal Payment Library");
	        	_progressDialog.setCancelable(false);
	        	_progressDialog.show();	
	        	Thread newThread = new Thread(checkforPayPalInitRunnable);
	        	newThread.start();
	    	} 
			
	}   //loadReviewPage
	   public void loadResultsPage() {
	    	setContentView(R.layout.gameonresultpurchase);
	    	
			//Set up the click listeners for the buttons
			findViewById(R.id.BuyMore).setOnClickListener(this);
			findViewById(R.id.Done).setOnClickListener(this);
	    }  //loadResultsPage

    public void onResume() {
		super.onResume();
    } //onResume

    protected void startPayPalInit() {
    	
        // Fire off a thread to do some work that we shouldn't do directly in the UI thread
        Thread t = new Thread() {
            public void run() {
                initPayPal();
            }
        };
        t.start(); 
    }
    private void initPayPal() {
	   	//Initialize the PayPal Library -- this is happening in a background thread
	   	
	   	//Passing through the app id and the server (Sandbox in this case)
	   	if(ppObj == null) {
	   		ppObj = PayPal.initWithAppID(this.getBaseContext(), gameOnProperties.getProperty("PAYPAL_APP_ID"), PayPal.ENV_SANDBOX);
	   	} else {
	   		ppObj = PayPal.getInstance();
	   	}
	   	
	   	//Set the language
	   	ppObj.setLang("en_US");
	   	
	   	//Set whether shipping is enabled. If shipping is enabled, the user will be shown the shipping
	   	//address and get a chance to change to a different shipping address
	   	ppObj.setShippingEnabled(false);
	   	
	   	//Enabling dynamic amount calculation will let us modify the tax and shipping amount
	   	//based on the address the user chose. If we enable this, we must be sure to
	   	//pass through our "AdjustAmounts" class to the PayPal Library activity 
	 //  	ppObj.enableDynamicAmountCalculation();
	    	
	   	//Get the "Pay with PayPal button" -- it will be added to the screen later
	   	_launchPayPalButton = ppObj.getPaymentButton(PayPal.BUTTON_278x43, this, PayPal.PAYMENT_TYPE_SERVICE);
	   	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	   	params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	   	params.bottomMargin = 10;
	   	_launchPayPalButton.setLayoutParams(params);
	   	_launchPayPalButton.setId(PAYPAL_BUTTON_ID);
	   	_launchPayPalButton.setOnClickListener(this);
	  	_paypalLibraryInit = true;//the PayPal Library is initialized, set the boolean to true
	}
    private void showPayPalButton() {
        // Back in the UI thread -- show the "Pay with PayPal" button
    	   if (null == _launchPayPalButton.getParent()) {
    		   ((RelativeLayout) findViewById(R.id.reviewPurchaseRelativeLayout)).addView(_launchPayPalButton);
    		   ((RelativeLayout) findViewById(R.id.reviewPurchaseRelativeLayout)).setGravity(Gravity.CENTER_HORIZONTAL);
    		   _progressDialog.dismiss();
    	   }
    }

    //This method is called if the Review page is being loaded but the PayPal Library is not
    //initialized yet. 
    private void checkForPayPalLibraryInit() {
    	//Loop as long as the library is not initialized
    	while (_paypalLibraryInit == false) {
    		try {
    			//wait 1/2 a second then check again
				Thread.sleep(500);
			} catch (InterruptedException e) {
				//Show an error to the user
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Error initializing PayPal Library")
				       .setCancelable(false)
				       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				               //Could do anything here to handle the error
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();
			}
    	}
    	//If we got here, it means the library is initialized.
    	//So, add the "Pay with PayPal" button to the screen
    	runOnUiThread(showPayPalButtonRunnable);
    }
    
	@Override
	public void onClick(View v) {

		if (v == (Button)findViewById(R.id.reviewOrderButton)) {
			loadReviewPage();
		} else 
		if (v ==(Button)getDoneButton()) {
//finish if on main screen, otherwise , go back one screen
		  if (inPage == REVIEW_PAGE) loadMainPage(); else finish();
		} else 
		if (v ==(Button)findViewById(R.id.BuyMore)) {
			loadMainPage();
		} else
		if (v == (CheckoutButton)findViewById(PAYPAL_BUTTON_ID)) {
			if (_launchedPayment == false) {
				//finish();
		    	_launchedPayment = true;
		    	//When the user presses the 'Pay with PayPal' button, set up the new payment
		    	PayPalPayment newPayment = new PayPalPayment();
		    	String subTotalString = _df.format(getDuckettsToPurchaseInDollars());
		    	subTotalString = subTotalString.replace("$", "");
		    	//Set the payment amount
		    	newPayment.setAmount(subTotalString);
		    	String taxString = _df.format(getTaxAmount());
		    	taxString = taxString.replace("$","");
		    	
		    	//Set the payment tax amount
		    	newPayment.setTax(taxString);

		    	//Set the payment currency
		    	newPayment.setCurrency("USD");
		    	
		    	//Set the recipient. This is the account that will receive the payment
		    	newPayment.setRecipient(gameOnProperties.getProperty("PAYPAL_MERCHANT_EMAIL"));
		    	
		    	//Sets the merchant name that is displayed to the end-user
		    	newPayment.setMerchantName(gameOnProperties.getProperty("PAYPAL_MERCHANT_NAME"));
		    	
		    	//Sets the item description that is displayed by the user
		    	newPayment.setItemDescription(gameOnProperties.getProperty("PAYPAL_MERCHANDISE_DESCRIPTION"));

		    	//Set up the PayPal intent 
		    	Intent payPalIntent = new Intent(GameOnManageBankActivity.this, PayPalActivity.class);
		    	//Put in the payment object
	        	payPalIntent.putExtra(PayPalActivity.EXTRA_PAYMENT_INFO, newPayment);
	
		    //	AdjustAmounts adjustClass = new AdjustAmounts();
				
				//Put in the Adjust Amounts class (this is used when the user selects an address,
				//to adjust the shipping amount or tax based on the user's address
			//	payPalIntent.putExtra(PayPalActivity.EXTRA_PAYMENT_ADJUSTER, adjustClass);

				//Start the PayPal Library activity
		    	this.startActivityForResult(payPalIntent, 1);
			} //if			
		} //if
	}  //onClick

	//PayPal Activity Results. This handles all the responses from the PayPal Payments Library
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Reset the _launchedPayment variable so the user can press "Pay with PayPal" again
		_launchedPayment = false;

		//Avoid an exception for setting a parent more than once
		if(_launchPayPalButton != null) {
  		//  ((RelativeLayout) findViewById(R.id.reviewPurchaseRelativeLayout)).removeView(_launchPayPalButton);		
		}
		
		if (requestCode != 1) {//We called "startActivityForResult" with resultCode of 1, so 
			//only handle where requestCode = 1
			return;
		}
		
		switch(resultCode) {
		case Activity.RESULT_OK:
			//The payment succeeded
			String transactionID = data.getStringExtra(PayPalActivity.EXTRA_TRANSACTION_ID);
			this.paymentSucceeded(transactionID);
			break;
		case Activity.RESULT_CANCELED:
			//The payment was canceled
			this.paymentCanceled();
			break;
		case PayPalActivity.RESULT_FAILURE:
			//The payment failed -- we get the error from the EXTRA_ERROR_ID and EXTRA_ERROR_MESSAGE
			String errorID = data.getStringExtra(PayPalActivity.EXTRA_ERROR_ID);
			String errorMessage = data.getStringExtra(PayPalActivity.EXTRA_ERROR_MESSAGE);
			System.out.println(errorMessage);
			this.paymentFailed(errorID, errorMessage);
			break;
		} //switch
	}  //onActivityResult

	/* payment succeeded 	
	 * Display status message
	 */
	private void paymentSucceeded(String transactionID) {
    	//We could show the transactionID to the user
		inPage = STATUS_PAGE;
		BSUserBankStatement response = updateBankBalance(transactionID);  //upDate Host user bank Statement Record
		if (response.getStatus_code().equals("200")) {
		   loadResultsPage();
		   ((TextView)findViewById(R.id.ResultsTitle)).setText(R.string.purchasesucceeded);
		   ((TextView)findViewById(R.id.ResultsText2)).setText("Estimated time: 30 minutes.");
		   ((TextView)findViewById(R.id.ResultsText3)).setText("Transaction ID: " + transactionID);
		} else {
			paymentFailed(response.getStatus_code(),response.getStatus_message());
		}
	}  // paymentsucceeded

	/*update host based BankStatement - use status_code as return value 200 is OK, everything else is an error */
	private BSUserBankStatement updateBankBalance(String transactionID) {

		HashMap<String,String> params = new HashMap<String,String>();
		params.put("userid", getAppContext().getUserIDString());
		params.put("transactiontypeid", String.valueOf(Consts.TRANSACTION_PURCHASE_DUCKETTS));
		params.put("transactionid", transactionID);
		params.put("transactionprovider", "paypal");
		params.put("transactionamountcurrency", String.valueOf(duckettsToPurchaseInDollars));
		params.put("transactionamountducketts", String.valueOf(duckettsToPurchase));
		params.put("transactiontypename", "purchase");
		
		if (null == bsAPI ) bsAPI = new BSClientAPIImpl();
		BSUserBankStatement response = bsAPI.updUserBankBalance(params);
		Log.d(TAG, response.toString());
		params = null; //reset/release

        return response;		
	} //updateBankBalance
	

	/* convenience - set user BankStatement Properties - host stored */
	private void setVariablesFromResponse(BSUserBankStatement response) {
          setDuckettsBankBalance( response.getDuckettsBankBalance());	
          setDuckettsInPlay( response.getDuckettsInPlay());
	}  //setVariablesFromResponse
	
	private void paymentCanceled() {
    	//We could tell the user that the payment was canceled
		inPage=CANCELED_PAGE;
		loadResultsPage();
		((TextView)findViewById(R.id.ResultsTitle)).setText(R.string.purchasecanceled);
		((TextView)findViewById(R.id.ResultsText1)).setText(R.string.purchasecanceled2);
		((TextView)findViewById(R.id.ResultsText2)).setText(R.string.purchasecanceled3);
		((TextView)findViewById(R.id.ResultsText3)).setText("");
		((Button)findViewById(R.id.BuyMore)).setText(R.string.tryagain);
	} //paymentCancelled
	
	private void paymentFailed(String errorID, String errorMessage) {
		//We could let the user know the payment failed here
		inPage=FAILURE_PAGE;
		loadResultsPage();
		((TextView)findViewById(R.id.ResultsTitle)).setText(R.string.purchasefailure);
		((TextView)findViewById(R.id.ResultsText1)).setText(R.string.purchasefailure2);
		((TextView)findViewById(R.id.ResultsText2)).setText("Error: " + errorMessage);
		((TextView)findViewById(R.id.ResultsText3)).setText("Error ID: " + errorID);
		((Button)findViewById(R.id.BuyMore)).setText(R.string.tryagain);
	}  //paymentFailed
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	public class AdjustAmounts implements PaymentAdjuster, Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1423827998570968857L;

		public MEPAmounts adjustAmount(MEPAddress address, String currency, float amount, float tax, float shipping) {
			MEPAmounts amounts = new MEPAmounts();
			
			//demo adjust amounts implementation that makes the tax 10% of the amount if 
			//the state is California, 8% otherwise, and leaves the rest the same
			amounts.setCurrency(currency);
			amounts.setPaymentAmount(amount);
			
			//make sure we do the null checks on the state string...
			if (address.getState() != null && address.getState().length() > 0 && address.getState().indexOf("CA") > -1) {
				float taxAmount = amount * .1f;
				DecimalFormat twoDForm = new DecimalFormat("#.##");
				amounts.setTax(Float.valueOf(twoDForm.format(taxAmount)));
			}
			else {
				float taxAmount = amount * .08f;
				DecimalFormat twoDForm = new DecimalFormat("#.##");
				amounts.setTax(Float.valueOf(twoDForm.format(taxAmount)));
			}
			amounts.setShipping(shipping);
				
			return amounts;
		}
	}
	public int getDuckettsBankBalance() {
		return duckettsBankBalance;
	}
	public void setDuckettsBankBalance(int duckettsBankBalance) {
		this.duckettsBankBalance = duckettsBankBalance;
	}
	public int getDuckettsInPlay() {
		return duckettsInPlay;
	}
	public void setDuckettsInPlay(int duckettsInPlay) {
		this.duckettsInPlay = duckettsInPlay;
	}
	public int getDuckettsToPurchase() {
		return duckettsToPurchase;
	}
	public void setDuckettsToPurchase(int duckettsToPurchase) {
		this.duckettsToPurchase = duckettsToPurchase;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setDuckettsToPurchaseInDollars(double duckettsToPurchaseInDollars) {
		this.duckettsToPurchaseInDollars = duckettsToPurchaseInDollars;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

}
