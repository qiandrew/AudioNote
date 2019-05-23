package com.example.audionote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ScrollView transcript_view;
    TextView transcript_text;
    ArrayList<KeyWord> keyWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        transcript_view = findViewById(R.id.transcript_view);
        transcript_text = findViewById(R.id.transcript_text);


        transcript_text.setText("Federal prosecutors in New York charged Michael Avenatti with additional financial crimes Wednesday, including allegedly forging the signature of his former client Stormy Daniels and diverting nearly $300,000 owed to her for a book advance into his own account, according to court records filed on Wednesday.\n" +
                "\n" +
                "Interested in Michael Avenatti?\n" +
                "Add Michael Avenatti as an interest to stay up to date on the latest Michael Avenatti news, video, and analysis from ABC News.\n" +
                "Michael Avenatti Add Interest\n" +
                "Prosecutors said that he then used money he took from Daniels to make monthly payments on his Ferrari, as well as to cover airfare, dry cleaning, hotels and restaurant bills, as well as payroll and insurance costs for his law firm's employees.\n" +
                "\n" +
                "The new charges accuse Avenatti of misappropriating money that was supposed to be paid to Daniels when Avenatti was representing the adult film actress in her public battle against President Trump and his former attorney Michael Cohen.\n" +
                "\n" +
                "\"Avenatti used misrepresentations and a fraudulent document purporting to bear his client's name and signature to convince his client's literary agent to divert money owed to Avenatti's client to an account controlled by Avenatti,\" Manhattan U.S. Attorney Geoffrey Berman said in a statement. \"Avenatti then spent the money principally for his own personal and business purposes.\"\n" +
                "\n" +
                "Federal prosecutors in Manhattan -- who have already accused Avenatti of extortion in a case involving Nike -- also indicted him separately on extortion charges in the Nike case on Wednesday.\n" +
                "\n" +
                "On Wednesday, Avenatti denied the fraud and identity theft charges to ABC News, saying that “No monies relating to Ms. Daniels were ever misappropriated or mishandled.\"\n" +
                "\n" +
                "\"She received millions of dollars worth of legal services and we expended huge sums in expenses,\" he wrote in a statement to ABC News.\n" +
                "\n" +
                "\"She directly paid only $100.00 (not a typo) for all that she received.\"\n" +
                "\n" +
                "Not so, said Berman in a statement.\n" +
                "\n" +
                "Federal prosecutors charge in court papers that Avenatti forged Daniels signature on a document for the purpose of diverting the funds she was owed into an account of his own, and leaving Daniels with the impression that the publishing house she was contracted with to write a book had not delivered the promised advances.\n" +
                "\n" +
                "\"A month after diverting one payment of $148,750 into his own account, Avenatti allegedly used funds received from another source to pay\" Daniels, prosecutors said in the statement.\n" +
                "\n" +
                "One week after that, according to prosecutors, Avenatti diverted a second payment of $148,750 to Daniels into his own account.\n" +
                "\n" +
                "\"To conceal his scheme, and despite repeated requests to Avenatti as [Daniels'] lawyer, for assistance in obtaining the book payment that [Daniels] believed was missing, Avenatti led [her] to believe that [her] publisher was refusing to make the payment...,\" prosecutors claim.\n" +
                "\n" +
                "Avenatti \"abused and violated the core duty of an attorney -- the duty to his client,\" Berman charged in the statement. \"As alleged, he used his position of trust to steal an advance on the client's book deal. As alleged, he blatantly lied to and stole from his client to maintain his extravagant lifestyle, including to pay for, among other things, a monthly car payment on a Ferrari. Far from zealously representing his client, Avenatti, as alleged, instead engaged in outright deception and theft, victimizing rather than advocating for his client.\"\n" +
                "\n" +
                "(MORE: Attorney Michael Avenatti arrested in alleged Nike extortion scheme: Prosecutors)\n" +
                "In a subsequent statement to ABC News after the charges were filed, Avenatti again reiterated his innocence and said he was entitled to the money from the book advance.\n" +
                "\n" +
                "\"I look forward to a jury hearing all of the evidence and passing judgment on my conduct,\" Avenatti wrote in the statement. \"At no time was any money misappropriated or mishandled. I will be fully exonerated once the relevant emails, contracts, text messages, and documents are presented. I was entitled to any monies retained relating to a book per my agreement with the client. It was part of my agreement for representation and compensation.\n" +
                "\n" +
                "Daniels' current attorney, Clark Brewster, had a different take when contacted by ABC News.\n" +
                "\n" +
                "“I doubt Michael Avenatti is looking forward to jury hearing evidence about his blatant dishonesty and embezzlement of Stormy’s book advances,\" Brewster told ABC News. \"If that sentiment is true he has a delusional disorder that merits attention.”\n" +
                "\n" +
                "News of prosecutors’ inquiry into his business dealings with Daniels marks the latest legal blow for Avenatti, coming after federal prosecutors on both coasts unsealed charges against the controversial 48-year-old attorney.\n" +
                "\n" +
                "(MORE: Lawyer Michael Avenatti could owe his estranged wife and child $1.5 million in spousal and child support)\n" +
                "The Daniels investigation is not related to those charges. But it is being run by the same team of federal officials who slapped Avenatti with two counts of extortion for his alleged role in what prosecutors called “an old-fashioned shakedown” of Nike.\n" +
                "\n" +
                "He and another celebrity lawyer, Mark Geragos, allegedly threatened to release damaging information about the sportswear giant if it refused to pay the two attorneys millions of dollars. Geragos was not charged with a crime.\n" +
                "\n" +
                "In the Central District of California, prosecutors targeted Avenatti with wire- and bank-fraud charges in a scheme that included stealing funds from a client to pay off his own expenses.\n" +
                "\n" +
                "Avenatti gained prominence last year when he began representing Daniels in a defamation lawsuit against Trump. A federal judge in California later threw out the suit and ordered Daniels to reimburse Trump for legal fees.");


    }

    public void displayTranscript(Transcript transcript) {
        transcript_text.setText(transcript.getText());

    }
}
