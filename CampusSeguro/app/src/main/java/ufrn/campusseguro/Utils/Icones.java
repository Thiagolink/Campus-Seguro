package ufrn.campusseguro.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by Wesley Reuel on 06/12/2017.
 */

public class Icones {

    public Bitmap resizeMapIcons(String iconName, int width, int height, Context ctx){
        Bitmap imageBitmap = BitmapFactory.decodeResource(ctx.getResources(),ctx.getResources().getIdentifier(iconName, "drawable", ctx.getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
    public static BitmapDescriptor iconePadrao(int icone, Context ctx){
        BitmapDrawable bitmapdraw = (BitmapDrawable) ctx.getResources().getDrawable(icone);
        final Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 60, 60, false);  //277 / 4222
        BitmapDescriptor iconePadrao = BitmapDescriptorFactory.fromBitmap(smallMarker);
        return iconePadrao;

    }
}
