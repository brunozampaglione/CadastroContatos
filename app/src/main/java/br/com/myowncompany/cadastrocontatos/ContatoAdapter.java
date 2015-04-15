package br.com.myowncompany.cadastrocontatos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by android5193 on 13/04/15.
 */
public class ContatoAdapter extends BaseAdapter {
    private activity_lista_contatos activity;
    private List<Contato> contatos;
    public ContatoAdapter(List<Contato> contatos, activity_lista_contatos act){
        this.contatos = contatos;
        this.activity = act;
    }
    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        ImageView foto = (ImageView) layout.findViewById(R.id.ivFoto);
        TextView nome = (TextView) layout.findViewById(R.id.lblNomeCont);

        Contato contato = contatos.get(position);
        nome.setText(contato.getNome());

        Bitmap bm;
        if(contato.getFoto()!=null) {
            // foto.setImageURI(Uri.parse(contato.getFoto()));

            bm = BitmapFactory.decodeFile(contato.getFoto());
            bm = Bitmap.createScaledBitmap(bm, 50, 50, true);
            bm = getCroppedBitmap(bm, 100);
            foto.setImageBitmap(bm);

        }
        else{

            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
            bm = getCroppedBitmap(bm, 100);
            foto.setImageBitmap(bm);

        }


        if(position%2==0){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        }
        else{
            layout.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }

        return layout;
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;

        if(bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));

        canvas.drawCircle(sbmp.getWidth() / 2+0.7f,
                          sbmp.getHeight() / 2+0.7f,
                          sbmp.getWidth() / 2+0.1f, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }
}
