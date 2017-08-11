package hali.pro.com.haliyikama.servisresources;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import hali.pro.com.haliyikama.R;
import hali.pro.com.haliyikama.dto.AdresTelefon;
import hali.pro.com.haliyikama.dto.SirketDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.helper.Utility;
import hali.pro.com.haliyikama.islemler.DataIslem;
import hali.pro.com.haliyikama.serverapplication.User;

public class FirmaEkleGuncelle extends Activity implements View.OnClickListener {
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Utility utility;
    Button btnFirmaResim, btnFirmaKaydet, btnFirmaGuncelle, btnFirmaSil, btnFirmaTemizle, btnLisansSonaErmeTarihi;
    EditText txtFirmaAdi, txtFirmaEPosta, txtFirmaSifre, txtFirmaAdres,
            txtFirmaTelefon, txtLisansSonaErmeTarihi, txtFirmaKalanSMS;
    private SirketDTO currentSirket;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private ImageView firmaLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma_ekle_guncelle);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnFirmaResim.setOnClickListener(this);
        btnFirmaGuncelle.setOnClickListener(this);
        btnFirmaKaydet.setOnClickListener(this);
        btnFirmaSil.setOnClickListener(this);
        btnFirmaTemizle.setOnClickListener(this);
        btnLisansSonaErmeTarihi.setOnClickListener(this);
    }

    private void init() throws IOException {
        // Button's
        btnFirmaResim = (Button) findViewById(R.id.btnFirmaResimSec);
        btnFirmaGuncelle = (Button) findViewById(R.id.btnFirmaEkleGuncelle);
        btnFirmaKaydet = (Button) findViewById(R.id.btnFirmaEkleKaydet);
        btnFirmaSil = (Button) findViewById(R.id.btnFirmaEkleSil);
        btnFirmaTemizle = (Button) findViewById(R.id.btnFirmaEkleRefresh);
        btnLisansSonaErmeTarihi = (Button) findViewById(R.id.btnFirmaLisansSonaErme);

        // EditText's
        txtFirmaAdi = (EditText) findViewById(R.id.txtFirmaAdi);
        txtFirmaAdres = (EditText) findViewById(R.id.txtFirmaEkleAdres);
        txtFirmaEPosta = (EditText) findViewById(R.id.txtFirmaEPosta);
        txtFirmaSifre = (EditText) findViewById(R.id.txtFirmaSifre);
        txtFirmaTelefon = (EditText) findViewById(R.id.txtFirmaEkleTelefon);
        txtLisansSonaErmeTarihi = (EditText) findViewById(R.id.txtFirmaLisansSonaErme);
        txtFirmaKalanSMS = (EditText) findViewById(R.id.txtFirmaKalanSms);

        // Image View's
        firmaLogo = (ImageView) findViewById(R.id.imageFirmaLogo);

        currentSirket = (SirketDTO) getIntent().getSerializableExtra("selectedSirket");
        Calendar cal = Calendar.getInstance();
        txtLisansSonaErmeTarihi.setText(sdf.format(cal.getTime()));
        utility = Utility.createInstance();
        if (currentSirket == null || currentSirket.getOid() == null) {
            currentSirket = new SirketDTO();
        } else {
            txtFirmaKalanSMS.setText(currentSirket.getKalanSms());
            txtLisansSonaErmeTarihi.setText(String.valueOf(currentSirket.getLisansEndTimes()));

            // Guncelleme İşlemleri Yapılacaktır
            DataIslem dataIslem = new DataIslem();
            User user = (User) dataIslem.get("User/" + currentSirket.getUserOid(), User.class, FirmaEkleGuncelle.this);

            txtFirmaEPosta.setText(user.getUserName());
            txtFirmaSifre.setText(user.getPassword());
            txtFirmaAdi.setText(user.getAdi());
            for (AdresTelefon adresTelefon : currentSirket.getLstAdresTel()) {
                if (adresTelefon.getTelOrAddres() == EnumUtil.TelOrAddres.ADDRES) {
                    txtFirmaAdres.setText(adresTelefon.getDeger());
                } else if (adresTelefon.getTelOrAddres() == EnumUtil.TelOrAddres.TELEFON) {
                    txtFirmaTelefon.setText(adresTelefon.getDeger());
                }
            }
        }
        currentSirket.setChangeImage(false);
    }

    private SirketDTO getSirketDTO(SirketDTO currentSirket) {
        Bitmap bitmap = ((BitmapDrawable) firmaLogo.getDrawable()).getBitmap();
        String encodedBitmap = utility.encodeTobase64(bitmap);
        currentSirket.setEncodedImages(encodedBitmap);
        try {
            currentSirket.setKalanSms(Integer.parseInt(txtFirmaKalanSMS.getText().toString().replace(" ", "")));
        } catch (Exception ex) {
            ex.printStackTrace();
            currentSirket.setKalanSms(0);
        }
        currentSirket.setLstAdresTel(getCurrentAdresTel());
        currentSirket.setSirketAdi(txtFirmaAdi.getText().toString());
        try {
            Date lisansSonaErme = sdf.parse(txtLisansSonaErmeTarihi.getText().toString());
            currentSirket.setLisansEndTimes(lisansSonaErme);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentSirket;
    }

    private List<AdresTelefon> getCurrentAdresTel() {
        List<AdresTelefon> lstAdresTel = new LinkedList<>();
        AdresTelefon adresTelefon = new AdresTelefon();
        adresTelefon.setTelTipi(EnumUtil.ContactTipi.GENEL);
        adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.TELEFON);
        adresTelefon.setDeger(txtFirmaTelefon.getText().toString());
        lstAdresTel.add(adresTelefon);

        adresTelefon = new AdresTelefon();
        adresTelefon.setAddresTipi(EnumUtil.AddresTipi.GENEL);
        adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.ADDRES);
        adresTelefon.setDeger(txtFirmaAdres.getText().toString());
        lstAdresTel.add(adresTelefon);

        return lstAdresTel;
    }

    private void selectImage() {
        final CharSequence[] items = {"Fotograf Cek ", "Galeriden Sec ",
                "Iptal "};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fotograf Ekle");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(FirmaEkleGuncelle.this);
                if (items[item].equals("Fotograf Cek ")) {
                    userChoosenTask = "Fotograf Cek";
                    if (result) {
                        cameraIntent();
                        currentSirket.setChangeImage(true);
                    }

                } else if (items[item].equals("Galeriden Sec ")) {
                    userChoosenTask = "Galeriden Sec ";
                    if (result) {
                        galleryIntent();
                        currentSirket.setChangeImage(true);
                    }
                } else if (items[item].equals("Iptal ")) {
                    dialog.dismiss();
                    currentSirket.setChangeImage(false);
                }
            }
        });
        builder.show();
    }


    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Dosya Secin"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Fotograf Çek"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Galeriden Yükle"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".png");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        firmaLogo.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        firmaLogo.setImageBitmap(bm);
    }

    @Override
    public void onClick(View v) {
        currentSirket = getSirketDTO(currentSirket);
        Intent intent = new Intent(this, login.class);
        switch (v.getId()) {
            case R.id.btnFirmaResimSec:
                selectImage();
                break;
            case R.id.btnFirmaLisansSonaErme:
                final Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtLisansSonaErmeTarihi.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, c.get(Calendar.YEAR) + 1, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;

            case R.id.btnFirmaEkleKaydet:
                try {
                    File directory = utility.createChildrenFolder("/data/data/haliYikama/Logolar/" + currentSirket.getSirketAdi() + "/"
                            + utility.createCurrentDate(), FirmaEkleGuncelle.this);
                    Bitmap bitmap = ((BitmapDrawable) firmaLogo.getDrawable()).getBitmap();
                    if (currentSirket.isChangeImage()) {
                        String androidImagePath = Utility.createInstance().saveImageInternalStroge(directory, bitmap, currentSirket.getSirketAdi());
                        currentSirket.setAndroidLogoPath(androidImagePath + "/" + currentSirket.getSirketAdi() + ".png");
                    }

                    DataIslem dataIslem = new DataIslem();
                    dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.PUT, "işlem başarılı", this,
                            currentSirket, "Firma/SirketDTO");


                } catch (IOException ex) {
                    Log.e("Siparis Kayit Hatasi", ex.getMessage());
                }
                startActivity(intent);
                break;
            case R.id.btnFirmaEkleGuncelle:
                try {
                    DataIslem dataIslem = new DataIslem();
                    dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.POST, "işlem başarılı", this,
                            currentSirket, "Firma/SirketDTO");

                } catch (IOException ex) {
                    Log.e("Siparis Kayit Hatasi", ex.getMessage());
                }
                startActivity(intent);
                break;
            case R.id.btnFirmaEkleSil:
                try {
                    DataIslem dataIslem = new DataIslem();
                    dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.DELETE, "işlem başarılu", this,
                            currentSirket, "Firma/SirketDTO");

                } catch (IOException ex) {
                    Log.e("Siparis Kayit Hatasi", ex.getMessage());
                }
                startActivity(intent);
                break;
            case R.id.btnFirmaEkleRefresh:

                break;
        }
    }


}
