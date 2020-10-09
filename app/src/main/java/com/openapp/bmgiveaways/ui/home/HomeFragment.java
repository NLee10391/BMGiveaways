package com.openapp.bmgiveaways.ui.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request.Method;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.openapp.bmgiveaways.EntryActivity;
import com.openapp.bmgiveaways.R;
import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class HomeFragment extends Fragment {


    public HomeFragment() { }

    private HomeViewModel homeViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final Button commonbtn = (Button) root.findViewById(R.id.Enterbtn_Common);
        final Button commonbtn2 = (Button) root.findViewById(R.id.Enterbtn_Common2);
        final Button commonbtn3 = (Button) root.findViewById(R.id.Enterbtn_Common3);


        commonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EntryActivity.class);
                intent.putExtra("Entered", "Contact info");
                startActivity(intent);
            }
        });
        return root;
    }
    public static class SliderPage extends AppCompatActivity
            implements  BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

        private SliderLayout sliderLayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.popup_termsofuse);

            sliderLayout = (SliderLayout) findViewById(R.id.slider);

            HashMap<String, Integer> HashMapForLocalRes = new HashMap<String, Integer>();

            HashMapForLocalRes.put("XBOX ONE SERIES X GIVEAWAY", R.drawable.xboxseriesx_banner_wide);
            HashMapForLocalRes.put("It's Launch Day!", R.drawable.xboxseriesx_banner_wide);
            HashMapForLocalRes.put("Gift Cards Galore!", R.drawable.xboxseriesx_banner_wide);

            for(String name : HashMapForLocalRes.keySet()){

                TextSliderView textSliderView = new TextSliderView(this);

                textSliderView
                        .description(name)
                        .image(HashMapForLocalRes.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                textSliderView.bundle(new Bundle());

                textSliderView.getBundle()
                        .putString("extra",name);

                sliderLayout.addSlider(textSliderView);
            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(3000);
            sliderLayout.addOnPageChangeListener(this);
        }
        @Override
        protected void onStop() {

            sliderLayout.stopAutoCycle();

            super.onStop();
        }
        @Override
        public void onSliderClick(BaseSliderView slider) {
            Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.d("Slider Demo", "Page Changed: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) { }
    }
}