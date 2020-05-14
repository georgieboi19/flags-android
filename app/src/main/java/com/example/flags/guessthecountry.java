package com.example.flags;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class guessthecountry extends AppCompatActivity {
    List<String> values = new ArrayList<String>();
    List<String> keys = new ArrayList<String>();
    Spinner spinner;
    int keySize = 0;
    int rNumber;
    TextView correctName,showTime,feedback;
    Button submitNext;
    boolean gamePlay, timerOn;
    CountDownTimer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessthecountry);
        timerOn = getIntent().getExtras().getBoolean("state");
        spinner = findViewById(R.id.spinner);
        JSON();
        randomImage();
        timerCountdown();
    }

    public void timerCountdown(){
        feedback = findViewById(R.id.textView_feedback);
        submitNext = findViewById(R.id.guess_submitbutton);
        showTime = findViewById(R.id.timerCount);
        if(timerOn) {
            myTimer = new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {
                    showTime.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    if (gamePlay) {
                        feedback.setText("WRONG");
                        feedback.setTextColor(Color.RED);
                        submitNext.setText("Next");
                        gamePlay = false;
                        myTimer.cancel();
                    }
                }

            }.start();
        }
    }

    public void JSON() {
        gamePlay=true;
        submitNext = (Button)findViewById(R.id.guess_submitbutton);
        submitNext.setText("Submit");
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
            JSONObject json = new JSONObject(jsonstring);
            Iterator<String> iterator = json.keys();
            while (iterator.hasNext()) {
                String obj = iterator.next();
                keys.add(obj);
                keySize++;
            }
            for (int i = 0; i < keys.size(); i++) {
                String temp = json.getString(keys.get(i));
                values.add(temp);
                ArrayAdapter<String> dataForAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, values);
                dataForAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataForAdapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void randomImage() {
        Random r = new Random();
        rNumber = r.nextInt(keySize);
        String rImage = (keys.get(rNumber));
        rImage = rImage.toLowerCase();
        ImageView img = (ImageView) findViewById(R.id.imageView_guess);
        Log.i("Country: ", rImage);
        int resourceId = getResources().getIdentifier(rImage, "drawable", "com.example.flags");
        img.setImageResource(resourceId);
    }

    public void submitGuess(View view) {
        feedback =findViewById(R.id.textView_feedback);
        correctName = findViewById(R.id.countryName);
        String rImage = (keys.get(rNumber));
        rImage = rImage.toLowerCase();
        //gets the spinner value to a string in order to check
        String spinnerIn = spinner.getSelectedItem().toString();
        submitNext = findViewById(R.id.guess_submitbutton);

        String rCountry = (values.get(rNumber));

        if (gamePlay) {

            if (rCountry.equals(spinnerIn)) {
                feedback.setText("Correct");
                feedback.setTextColor(Color.GREEN);
                if (timerOn) {
                    myTimer.cancel();
                }
            } else {
                feedback.setText("Wrong");
                feedback.setTextColor(Color.RED);
                correctName.setText(rCountry);
                if (timerOn) {
                    myTimer.cancel();
                }
            }
            submitNext.setText("Next");
            gamePlay = false;
        } else {
            feedback.setText("");
            submitNext.setText("Submit");
            correctName.setText("");
            JSON();
            randomImage();
            timerCountdown();
        }
    }
}



