package com.example.flags;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class guesshints extends AppCompatActivity {
//creates variables to be used publicaly
    List<String> values = new ArrayList<>();
    List<String> keys = new ArrayList<>();
    int keySize = 0;
    TextView cntryToGuess;
    TextView feedback,showTime;
    int rValue;
    String rWord;
    String rImage;
    EditText guess;
    int attempts;
    int correct;
    String output = "";
    Button submitNext;
    boolean incorrect, next;
    boolean timerOn;
    CountDownTimer myTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on create will get the boolean from main activity and store it in a local variable. also this will run the methods needed
        setContentView(R.layout.activity_guesshints);
        timerOn = getIntent().getExtras().getBoolean("state");
        JSON();
        setGuess();
        timerCountdown();


    }

    public void timerCountdown(){
        //reads in all the fields and store to a variable
        showTime = findViewById(R.id.timerCount);
        submitNext = findViewById(R.id.guess_submitbutton);
        cntryToGuess = findViewById(R.id.textView_guess);
        feedback = findViewById(R.id.textView_response);
        if(timerOn) {
            //creates a new 10 sec countdown timer
            myTimer = new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //constantly updates the text box on every tick
                    showTime.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    //when the timer has finished it will update the status i.e. incorrect
                    if (!next) {
                        feedback.setText("WRONG");
                        feedback.setTextColor(Color.RED);
                        cntryToGuess.setText(rWord);
                        cntryToGuess.setTextColor(Color.BLUE);
                        submitNext.setText("Next");
                        next = true;
                    }
                }

            }.start();
        }
    }

    public void JSON (){
        //storing the json data into a string to be read in a try/catch
        try {
            String jsonstring = "{\n" +
                    "    \"AD\": \"Andorra\",\n" +
                    "    \"AE\": \"United Arab Emirates\",\n" +
                    "    \"AF\": \"Afghanistan\",\n" +
                    "    \"AG\": \"Antigua and Barbuda\",\n" +
                    "    \"AI\": \"Anguilla\",\n" +
                    "    \"AL\": \"Albania\",\n" +
                    "    \"AM\": \"Armenia\",\n" +
                    "    \"AN\": \"Netherlands Antilles\",\n" +
                    "    \"AO\": \"Angola\",\n" +
                    "    \"AQ\": \"Antarctica\",\n" +
                    "    \"AR\": \"Argentina\",\n" +
                    "    \"AS\": \"American Samoa\",\n" +
                    "    \"AT\": \"Austria\",\n" +
                    "    \"AU\": \"Australia\",\n" +
                    "    \"AW\": \"Aruba\",\n" +
                    "    \"AX\": \"\\u00c5land Islands\",\n" +
                    "    \"AZ\": \"Azerbaijan\",\n" +
                    "    \"BA\": \"Bosnia and Herzegovina\",\n" +
                    "    \"BB\": \"Barbados\",\n" +
                    "    \"BD\": \"Bangladesh\",\n" +
                    "    \"BE\": \"Belgium\",\n" +
                    "    \"BF\": \"Burkina Faso\",\n" +
                    "    \"BG\": \"Bulgaria\",\n" +
                    "    \"BH\": \"Bahrain\",\n" +
                    "    \"BI\": \"Burundi\",\n" +
                    "    \"BJ\": \"Benin\",\n" +
                    "    \"BL\": \"Saint Barthélemy\",\n" +
                    "    \"BM\": \"Bermuda\",\n" +
                    "    \"BN\": \"Brunei Darussalam\",\n" +
                    "    \"BO\": \"Bolivia, Plurinational State of\",\n" +
                    "    \"BQ\": \"Caribbean Netherlands\",\n" +
                    "    \"BR\": \"Brazil\",\n" +
                    "    \"BS\": \"Bahamas\",\n" +
                    "    \"BT\": \"Bhutan\",\n" +
                    "    \"BV\": \"Bouvet Island\",\n" +
                    "    \"BW\": \"Botswana\",\n" +
                    "    \"BY\": \"Belarus\",\n" +
                    "    \"BZ\": \"Belize\",\n" +
                    "    \"CA\": \"Canada\",\n" +
                    "    \"CC\": \"Cocos (Keeling) Islands\",\n" +
                    "    \"CD\": \"Congo, the Democratic Republic of the\",\n" +
                    "    \"CF\": \"Central African Republic\",\n" +
                    "    \"CG\": \"Congo\",\n" +
                    "    \"CH\": \"Switzerland\",\n" +
                    "    \"CI\": \"C\\u00f4te d'Ivoire\",\n" +
                    "    \"CK\": \"Cook Islands\",\n" +
                    "    \"CL\": \"Chile\",\n" +
                    "    \"CM\": \"Cameroon\",\n" +
                    "    \"CN\": \"China\",\n" +
                    "    \"CO\": \"Colombia\",\n" +
                    "    \"CR\": \"Costa Rica\",\n" +
                    "    \"CU\": \"Cuba\",\n" +
                    "    \"CV\": \"Cape Verde\",\n" +
                    "    \"CW\": \"Cura\\u00e7ao\",\n" +
                    "    \"CX\": \"Christmas Island\",\n" +
                    "    \"CY\": \"Cyprus\",\n" +
                    "    \"CZ\": \"Czech Republic\",\n" +
                    "    \"DE\": \"Germany\",\n" +
                    "    \"DJ\": \"Djibouti\",\n" +
                    "    \"DK\": \"Denmark\",\n" +
                    "    \"DM\": \"Dominica\",\n" +
                    "    \"DI\": \"Dominican Republic\",\n" +
                    "    \"DZ\": \"Algeria\",\n" +
                    "    \"EC\": \"Ecuador\",\n" +
                    "    \"EE\": \"Estonia\",\n" +
                    "    \"EG\": \"Egypt\",\n" +
                    "    \"EH\": \"Western Sahara\",\n" +
                    "    \"ER\": \"Eritrea\",\n" +
                    "    \"ES\": \"Spain\",\n" +
                    "    \"ET\": \"Ethiopia\",\n" +
                    "    \"EU\": \"Europe\",\n" +
                    "    \"FI\": \"Finland\",\n" +
                    "    \"FJ\": \"Fiji\",\n" +
                    "    \"FK\": \"Falkland Islands (Malvinas)\",\n" +
                    "    \"FM\": \"Micronesia, Federated States of\",\n" +
                    "    \"FO\": \"Faroe Islands\",\n" +
                    "    \"FR\": \"France\",\n" +
                    "    \"GA\": \"Gabon\",\n" +
                    "    \"GBENG\": \"England\",\n" +
                    "    \"GBNIR\": \"Northern Ireland\",\n" +
                    "    \"GBSCT\": \"Scotland\",\n" +
                    "    \"GBWLS\": \"Wales\",\n" +
                    "    \"GB\": \"United Kingdom\",\n" +
                    "    \"GD\": \"Grenada\",\n" +
                    "    \"GE\": \"Georgia\",\n" +
                    "    \"GF\": \"French Guiana\",\n" +
                    "    \"GG\": \"Guernsey\",\n" +
                    "    \"GH\": \"Ghana\",\n" +
                    "    \"GI\": \"Gibraltar\",\n" +
                    "    \"GL\": \"Greenland\",\n" +
                    "    \"GM\": \"Gambia\",\n" +
                    "    \"GN\": \"Guinea\",\n" +
                    "    \"GP\": \"Guadeloupe\",\n" +
                    "    \"GQ\": \"Equatorial Guinea\",\n" +
                    "    \"GR\": \"Greece\",\n" +
                    "    \"GS\": \"South Georgia and the South Sandwich Islands\",\n" +
                    "    \"GT\": \"Guatemala\",\n" +
                    "    \"GU\": \"Guam\",\n" +
                    "    \"GW\": \"Guinea-Bissau\",\n" +
                    "    \"GY\": \"Guyana\",\n" +
                    "    \"HK\": \"Hong Kong\",\n" +
                    "    \"HM\": \"Heard Island and McDonald Islands\",\n" +
                    "    \"HN\": \"Honduras\",\n" +
                    "    \"HR\": \"Croatia\",\n" +
                    "    \"HT\": \"Haiti\",\n" +
                    "    \"HU\": \"Hungary\",\n" +
                    "    \"ID\": \"Indonesia\",\n" +
                    "    \"IE\": \"Ireland\",\n" +
                    "    \"IL\": \"Israel\",\n" +
                    "    \"IM\": \"Isle of Man\",\n" +
                    "    \"IN\": \"India\",\n" +
                    "    \"IO\": \"British Indian Ocean Territory\",\n" +
                    "    \"IQ\": \"Iraq\",\n" +
                    "    \"IR\": \"Iran, Islamic Republic of\",\n" +
                    "    \"IS\": \"Iceland\",\n" +
                    "    \"IT\": \"Italy\",\n" +
                    "    \"JE\": \"Jersey\",\n" +
                    "    \"JM\": \"Jamaica\",\n" +
                    "    \"JO\": \"Jordan\",\n" +
                    "    \"JP\": \"Japan\",\n" +
                    "    \"KE\": \"Kenya\",\n" +
                    "    \"KG\": \"Kyrgyzstan\",\n" +
                    "    \"KH\": \"Cambodia\",\n" +
                    "    \"KI\": \"Kiribati\",\n" +
                    "    \"KM\": \"Comoros\",\n" +
                    "    \"KN\": \"Saint Kitts and Nevis\",\n" +
                    "    \"KP\": \"Korea, Democratic People's Republic of\",\n" +
                    "    \"KR\": \"Korea, Republic of\",\n" +
                    "    \"KW\": \"Kuwait\",\n" +
                    "    \"KY\": \"Cayman Islands\",\n" +
                    "    \"KZ\": \"Kazakhstan\",\n" +
                    "    \"LA\": \"Lao People's Democratic Republic\",\n" +
                    "    \"LB\": \"Lebanon\",\n" +
                    "    \"LC\": \"Saint Lucia\",\n" +
                    "    \"LI\": \"Liechtenstein\",\n" +
                    "    \"LK\": \"Sri Lanka\",\n" +
                    "    \"LR\": \"Liberia\",\n" +
                    "    \"LS\": \"Lesotho\",\n" +
                    "    \"LT\": \"Lithuania\",\n" +
                    "    \"LU\": \"Luxembourg\",\n" +
                    "    \"LV\": \"Latvia\",\n" +
                    "    \"LY\": \"Libya\",\n" +
                    "    \"MA\": \"Morocco\",\n" +
                    "    \"MC\": \"Monaco\",\n" +
                    "    \"MD\": \"Moldova, Republic of\",\n" +
                    "    \"ME\": \"Montenegro\",\n" +
                    "    \"MF\": \"Saint Martin\",\n" +
                    "    \"MG\": \"Madagascar\",\n" +
                    "    \"MH\": \"Marshall Islands\",\n" +
                    "    \"MK\": \"Macedonia, the former Yugoslav Republic of\",\n" +
                    "    \"ML\": \"Mali\",\n" +
                    "    \"MM\": \"Myanmar\",\n" +
                    "    \"MN\": \"Mongolia\",\n" +
                    "    \"MO\": \"Macao\",\n" +
                    "    \"MP\": \"Northern Mariana Islands\",\n" +
                    "    \"MQ\": \"Martinique\",\n" +
                    "    \"MR\": \"Mauritania\",\n" +
                    "    \"MS\": \"Montserrat\",\n" +
                    "    \"MT\": \"Malta\",\n" +
                    "    \"MU\": \"Mauritius\",\n" +
                    "    \"MV\": \"Maldives\",\n" +
                    "    \"MW\": \"Malawi\",\n" +
                    "    \"MX\": \"Mexico\",\n" +
                    "    \"MY\": \"Malaysia\",\n" +
                    "    \"MZ\": \"Mozambique\",\n" +
                    "    \"NA\": \"Namibia\",\n" +
                    "    \"NC\": \"New Caledonia\",\n" +
                    "    \"NE\": \"Niger\",\n" +
                    "    \"NF\": \"Norfolk Island\",\n" +
                    "    \"NG\": \"Nigeria\",\n" +
                    "    \"NI\": \"Nicaragua\",\n" +
                    "    \"NL\": \"Netherlands\",\n" +
                    "    \"NO\": \"Norway\",\n" +
                    "    \"NP\": \"Nepal\",\n" +
                    "    \"NR\": \"Nauru\",\n" +
                    "    \"NU\": \"Niue\",\n" +
                    "    \"NZ\": \"New Zealand\",\n" +
                    "    \"OM\": \"Oman\",\n" +
                    "    \"PA\": \"Panama\",\n" +
                    "    \"PE\": \"Peru\",\n" +
                    "    \"PF\": \"French Polynesia\",\n" +
                    "    \"PG\": \"Papua New Guinea\",\n" +
                    "    \"PH\": \"Philippines\",\n" +
                    "    \"PK\": \"Pakistan\",\n" +
                    "    \"PL\": \"Poland\",\n" +
                    "    \"PM\": \"Saint Pierre and Miquelon\",\n" +
                    "    \"PN\": \"Pitcairn\",\n" +
                    "    \"PR\": \"Puerto Rico\",\n" +
                    "    \"PS\": \"Palestine\",\n" +
                    "    \"PT\": \"Portugal\",\n" +
                    "    \"PW\": \"Palau\",\n" +
                    "    \"PY\": \"Paraguay\",\n" +
                    "    \"QA\": \"Qatar\",\n" +
                    "    \"RE\": \"Réunion\",\n" +
                    "    \"RO\": \"Romania\",\n" +
                    "    \"RS\": \"Serbia\",\n" +
                    "    \"RU\": \"Russian Federation\",\n" +
                    "    \"RW\": \"Rwanda\",\n" +
                    "    \"SA\": \"Saudi Arabia\",\n" +
                    "    \"SB\": \"Solomon Islands\",\n" +
                    "    \"SC\": \"Seychelles\",\n" +
                    "    \"SD\": \"Sudan\",\n" +
                    "    \"SE\": \"Sweden\",\n" +
                    "    \"SG\": \"Singapore\",\n" +
                    "    \"SH\": \"Saint Helena, Ascension and Tristan da Cunha\",\n" +
                    "    \"SI\": \"Slovenia\",\n" +
                    "    \"SJ\": \"Svalbard and Jan Mayen Islands\",\n" +
                    "    \"SK\": \"Slovakia\",\n" +
                    "    \"SL\": \"Sierra Leone\",\n" +
                    "    \"SM\": \"San Marino\",\n" +
                    "    \"SN\": \"Senegal\",\n" +
                    "    \"SO\": \"Somalia\",\n" +
                    "    \"SR\": \"Suriname\",\n" +
                    "    \"SS\": \"South Sudan\",\n" +
                    "    \"ST\": \"Sao Tome and Principe\",\n" +
                    "    \"SV\": \"El Salvador\",\n" +
                    "    \"SX\": \"Sint Maarten (Dutch part)\",\n" +
                    "    \"SY\": \"Syrian Arab Republic\",\n" +
                    "    \"SZ\": \"Swaziland\",\n" +
                    "    \"TC\": \"Turks and Caicos Islands\",\n" +
                    "    \"TD\": \"Chad\",\n" +
                    "    \"TF\": \"French Southern Territories\",\n" +
                    "    \"TG\": \"Togo\",\n" +
                    "    \"TH\": \"Thailand\",\n" +
                    "    \"TJ\": \"Tajikistan\",\n" +
                    "    \"TK\": \"Tokelau\",\n" +
                    "    \"TL\": \"Timor-Leste\",\n" +
                    "    \"TM\": \"Turkmenistan\",\n" +
                    "    \"TN\": \"Tunisia\",\n" +
                    "    \"TO\": \"Tonga\",\n" +
                    "    \"TR\": \"Turkey\",\n" +
                    "    \"TT\": \"Trinidad and Tobago\",\n" +
                    "    \"TV\": \"Tuvalu\",\n" +
                    "    \"TW\": \"Taiwan\",\n" +
                    "    \"TZ\": \"Tanzania, United Republic of\",\n" +
                    "    \"UA\": \"Ukraine\",\n" +
                    "    \"UG\": \"Uganda\",\n" +
                    "    \"UM\": \"US Minor Outlying Islands\",\n" +
                    "    \"US\": \"United States\",\n" +
                    "    \"UY\": \"Uruguay\",\n" +
                    "    \"UZ\": \"Uzbekistan\",\n" +
                    "    \"VA\": \"Holy See (Vatican City State)\",\n" +
                    "    \"VC\": \"Saint Vincent and the Grenadines\",\n" +
                    "    \"VE\": \"Venezuela, Bolivarian Republic of\",\n" +
                    "    \"VG\": \"Virgin Islands, British\",\n" +
                    "    \"VI\": \"Virgin Islands, U.S.\",\n" +
                    "    \"VN\": \"Viet Nam\",\n" +
                    "    \"VU\": \"Vanuatu\",\n" +
                    "    \"WF\": \"Wallis and Futuna Islands\",\n" +
                    "    \"XK\": \"Kosovo\",\n" +
                    "    \"WS\": \"Samoa\",\n" +
                    "    \"YE\": \"Yemen\",\n" +
                    "    \"YT\": \"Mayotte\",\n" +
                    "    \"ZA\": \"South Africa\",\n" +
                    "    \"ZM\": \"Zambia\",\n" +
                    "    \"ZW\": \"Zimbabwe\"\n" +
                    "}\n";
            //creates a json object from the string above and then iterates through the keys (GB etc)
            JSONObject json = new JSONObject(jsonstring);
            Iterator<String> iterator = json.keys();
            //while there is another key for the itterator to read it will sort it to an array list
            while (iterator.hasNext()) {
                String obj = iterator.next();
                keys.add(obj);
                keySize++;
            }
            //this will store the country names into another array list so that it can be called seperatly
            for (int i = 0; i < keys.size(); i++) {
                String temp = json.getString(keys.get(i));
                values.add(temp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setGuess(){
        //here i reset values from a previous game try and also read the attributes to variables
        cntryToGuess =findViewById(R.id.textView_guess);
        feedback = findViewById(R.id.textView_response);
        submitNext = findViewById(R.id.guess_submitbutton);
        attempts = 0;
        correct = 0;
        //selects a random number from the size of the array list and store the string at index 'x' into a variable
        Random r = new Random();
        rValue = r.nextInt(keySize+1);
        rWord = (values.get(rValue));
        rImage = (keys.get(rValue));
        feedback.setText("");
        cntryToGuess.setTextColor(Color.BLACK);
        next = false;
        output = "";
        submitNext.setText("Submit");
//i read the string in as lower case so that the input can be case insensitive
        rImage = rImage.toLowerCase();
        //selects the image from the file contanting all the images from that of the one that was picked by the random number and populates the image
        ImageView img = findViewById(R.id.imageView_guess);
        int resource_id = getResources().getIdentifier(rImage, "drawable", "com.example.flags");
        img.setImageResource(resource_id);
        Log.i("Country", rWord);

        for (int i = 0; i<rWord.length(); i++){
//store the char at i in order to read each letter correctly and print either a space or a '-' to the screen
            char c = rWord.charAt(i);
           if (c == ' '){
               output = output + " ";
           } else {
               output = output + "-";
           }
        }
        cntryToGuess.setText(output);

    }

    public void submitGuess(View view) {
        submitNext = findViewById(R.id.guess_submitbutton);
        guess = findViewById(R.id.editText_guess);
        cntryToGuess = findViewById(R.id.textView_guess);
        feedback = findViewById(R.id.textView_response);
        incorrect = true;
        if (!next) {
            if (attempts != 3 || correct != rWord.length()) {
                //read in the answer as lower case
                String answer = guess.getText().toString().toLowerCase();
                //getting the answer at '0' as it only expects one character
                char cAnswer = answer.charAt(0);
                //looks through the expected word and sees if the answer matches the correct country
                for (int i = 0; i < rWord.length(); i++) {
                    char c = rWord.charAt(i);
                    char lowerCase = Character.toLowerCase(c);
                    char[] myWordChars = output.toCharArray();

                    if (cAnswer == lowerCase) {
                        myWordChars[i] = c;
                        correct++;
                        incorrect = false;
                    }
                    output = String.valueOf(myWordChars);
                }
                if (incorrect) {
                    attempts++;
                }
                //depending on the outcome the text boxes will be updated
                cntryToGuess.setText(output);
                if (correct == rWord.length()) {
                    feedback.setText("CORRECT");
                    feedback.setTextColor(Color.GREEN);
                    submitNext.setText("Next");
                    next=true;
                    if (timerOn) {
                        myTimer.cancel();
                    }
                } else if (attempts == 3) {
                    feedback.setText("WRONG");
                    feedback.setTextColor(Color.RED);
                    cntryToGuess.setText(rWord);
                    cntryToGuess.setTextColor(Color.BLUE);
                    submitNext.setText("Next");
                    next = true;
                    //cancels timer as to not continue on another game
                    if (timerOn) {
                        myTimer.cancel();
                    }
                }
            }
        }else{
            //restarts the game again if it has ended and the button has been clicked
            setGuess();
            timerCountdown();
        }
    }
}
