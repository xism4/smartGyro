package android.support.v7.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.org.v4.util.R$id;

final class na$a {
    public final ImageView mIcon1;
    public final ImageView mIcon2;
    public final ImageView mIconRefine;
    public final TextView mText1;
    public final TextView mText2;

    public na$a(View view) {
        this.mText1 = (TextView) view.findViewById(16908308);
        this.mText2 = (TextView) view.findViewById(16908309);
        this.mIcon1 = (ImageView) view.findViewById(16908295);
        this.mIcon2 = (ImageView) view.findViewById(16908296);
        this.mIconRefine = (ImageView) view.findViewById(R$id.edit_query);
    }
}
