package id.beneranindonesia.pusaka.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by Yoshua Andrew on 06/06/18.
 */

public class ProfileFragment extends BaseFragment {

    private IntentIntegrator qrScan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView imageView = view.findViewById(R.id.QRCodeImageview);
        String text = "pusakamissionid=1";
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        qrScan = new IntentIntegrator(getActivity());

        Button button = view.findViewById(R.id.btnScanQRCode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
            }
        });

        ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).updateToolbarTitle("Profile");

        return view;
    }

}
























