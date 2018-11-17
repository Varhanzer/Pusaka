package id.beneranindonesia.pusaka.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.zxing.integration.android.IntentIntegrator;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;

import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.activities.OnBoard.OnBoardingActivity;
import id.beneranindonesia.pusaka.utils.Session;

/**
 * Created by Yoshua Andrew on 06/06/18.
 */

public class ProfileFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        ImageView imageView = view.findViewById(R.id.QRCodeImageview);
//        String text = "pusakamissionid=1";
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        try {
//            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
//            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//            imageView.setImageBitmap(bitmap);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }

        Button button = view.findViewById(R.id.btnScanQRCode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).updateToolbarTitle("Profile");

        return view;
    }

    private void logOut() {
        Session.getInstance().signOut(getActivity());
        Intent intent = new Intent(getActivity(), OnBoardingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
























