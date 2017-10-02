package hali.pro.com.haliyikama.servisresources;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;

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
import hali.pro.com.haliyikama.dto.CalisanDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;
import hali.pro.com.haliyikama.islemler.DataIslem;

public class PersonelEkleGuncellee extends AppCompatActivity implements View.OnClickListener {
    private DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private EditText txtPersonelAd, txtPersonelSoyad, txtPersonelEmail, txtPersonelSifre,
            txtPersonelAdres, txtPersonelTelefon, txtPersonelIseBaslamaTarihi;
    private Button btnKaydet, btnGuncelle, btnSil, btnYenile, btnTarihSec;

    private TableRow rowKaydet, rowGuncelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_ekle_guncellee);
        init();
        btnTarihSec.setOnClickListener(this);
        btnGuncelle.setOnClickListener(this);
        btnKaydet.setOnClickListener(this);
        btnSil.setOnClickListener(this);
        btnYenile.setOnClickListener(this);
    }

    private void init() {
        txtPersonelAd = (EditText) findViewById(R.id.txtPersonelAd);
        txtPersonelSoyad = (EditText) findViewById(R.id.txtPersonelSoyad);
        txtPersonelEmail = (EditText) findViewById(R.id.txtPersonelEmail);
        txtPersonelSifre = (EditText) findViewById(R.id.txtPersonelSifresi);
        txtPersonelAdres = (EditText) findViewById(R.id.txtPersonelAdres);
        txtPersonelTelefon = (EditText) findViewById(R.id.txtPersonelTelefon);
        txtPersonelIseBaslamaTarihi = (EditText) findViewById(R.id.txtPersonelIseGirisTarihi);

        btnKaydet = (Button) findViewById(R.id.btnPersonelKaydet);
        btnGuncelle = (Button) findViewById(R.id.btnPersonelGuncelle);
        btnSil = (Button) findViewById(R.id.btnPersonelSil);
        btnYenile = (Button) findViewById(R.id.btnPersonelRefresh);
        btnTarihSec = (Button) findViewById(R.id.btnPersonelIseGiris);

        rowKaydet = (TableRow) findViewById(R.id.rowPersonelEkleme);
        rowGuncelle = (TableRow) findViewById(R.id.rowPersonelSilme);
    }

    private CalisanDTO currentCalisan(CalisanDTO calisanDTO) {
        List<AdresTelefon> lstAdresTelefon = new LinkedList<>();
        ;
        if (calisanDTO == null) {
            calisanDTO = new CalisanDTO();
            Log.i("yeni personel kaydi", Calendar.getInstance().toString());
        }
        calisanDTO.setAd(txtPersonelAd.getText().toString());
        calisanDTO.setSoyad(txtPersonelSoyad.getText().toString());
        calisanDTO.setEmployeeType(EnumUtil.EmployeeType.ISCİ);
        calisanDTO.setSifre(txtPersonelSifre.getText().toString());
        calisanDTO.setKullaniciAdi(txtPersonelEmail.getText().toString());

        Date iseBaslangic = null;
        try {
            iseBaslangic = sdf.parse(txtPersonelIseBaslamaTarihi.getText().toString());
            calisanDTO.setIseGirisTarihi(iseBaslangic);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AdresTelefon adresTelefon = new AdresTelefon();
        adresTelefon.setAddresTipi(EnumUtil.AddresTipi.GENEL);
        adresTelefon.setDeger(txtPersonelAdres.getText().toString());
        adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.ADDRES);
        lstAdresTelefon.add(adresTelefon);

        adresTelefon = new AdresTelefon();
        adresTelefon.setTelTipi(EnumUtil.ContactTipi.GENEL);
        adresTelefon.setTelOrAddres(EnumUtil.TelOrAddres.TELEFON);
        adresTelefon.setDeger(txtPersonelTelefon.getText().toString());
        lstAdresTelefon.add(adresTelefon);

        calisanDTO.setLstAddresTel(lstAdresTelefon);

        return calisanDTO;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, login.class);
        switch (v.getId()) {
            case R.id.btnPersonelKaydet:
                try {
                    CalisanDTO currentCalisan = currentCalisan(null);
                    DataIslem dataIslem = new DataIslem();
                    dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.PUT, "işlem başarılı", this,
                            currentCalisan, "Employee/CalisanDTO");


                } catch (IOException ex) {
                    Log.e("Calisan Kayit Hatasi", ex.getMessage());
                }
                startActivity(intent);
                break;
            case R.id.btnPersonelGuncelle:
                break;
            case R.id.btnPersonelSil:
                try {
                    CalisanDTO currentCalisan = currentCalisan(null);
                    DataIslem dataIslem = new DataIslem();
                    dataIslem.updateDeleteCreateProcess(EnumUtil.SendingDataType.DELETE, "işlem başarılı", this,
                            currentCalisan, "Employee/CalisanDTO");


                } catch (IOException ex) {
                    Log.e("Calisan Silme Hatasi", ex.getMessage());
                }
                startActivity(intent);
                break;
            case R.id.btnPersonelRefresh:
                startActivity(new Intent(PersonelEkleGuncellee.this, PersonelEkleGuncellee.class));
                break;
            case R.id.btnPersonelIseGiris:
                final Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                txtPersonelIseBaslamaTarihi.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, c.get(Calendar.YEAR) + 1, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            default:
                break;
        }
    }
}
