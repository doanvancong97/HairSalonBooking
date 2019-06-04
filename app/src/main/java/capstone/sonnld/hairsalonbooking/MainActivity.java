package capstone.sonnld.hairsalonbooking;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import capstone.sonnld.hairsalonbooking.DTO.Salon;
import capstone.sonnld.hairsalonbooking.DTO.SalonService;
import capstone.sonnld.hairsalonbooking.Fragment.PagerAdapter;
import capstone.sonnld.hairsalonbooking.Fragment.TabViewSalon;
import capstone.sonnld.hairsalonbooking.Fragment.TabBooking;
import capstone.sonnld.hairsalonbooking.ViewAdapter.RecyclerViewAdapter;
import capstone.sonnld.hairsalonbooking.ViewAdapter.RecyclerViewNewestAdapter;
import capstone.sonnld.hairsalonbooking.ViewAdapter.RecyclerViewServiceAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private Toolbar mToolbar;

    List<Salon> salonList;
    List<SalonService> salonServiceList;

    private SearchView searchView;
    private TextView txtDumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup tool bar
        mToolbar = findViewById(R.id.nav_action_bar);
        setSupportActionBar(mToolbar);

        searchView = (SearchView) findViewById(R.id.seachView);
        searchView.clearFocus();

        txtDumb = findViewById(R.id.txt_dummy);
        txtDumb.requestFocus();
        //setup sideBar
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //end setup sideBar

        String des1 = "ÁP DỤNG KHI DÙNG DỊCH VỤ TẠI CỬA HÀNG* \n" +
                "\n" +
                "- Giảm 20% tổng hóa đơn áp dụng cho tất cả các dịch vụ \n" +
                "- Áp dụng cho khách hàng nữ \n" +
                "- Mỗi mã ưu đãi đổi được nhiều suất trong suốt chương trình \n" +
                "- Khách hàng có thể lấy nhiều mã trong suốt chương trình \n" +
                "\n" +
                "THỜI GIAN ÁP DỤNG \n" +
                "- Khung giờ: 9h30 - 19h00\t\n" +
                "- Áp dụng tất cả các ngày trong tuần \n" +
                "- Không áp dụng các ngày lễ, Tết: 30/4, 1/5 \n" +
                "\n" +
                "Chi tiết địa điểm xem tại \"Điểm áp dụng\" \n" +
                "\n" +
                "Vui lòng bấm XÁC NHẬN ĐẶT CHỖ để nhận mã giảm giá \n" +
                "\n" +
                "LƯU Ý \n" +
                "- Chương trình chỉ áp dụng với khách dùng dịch vụ tại cửa hàng \n" +
                "- Không áp dụng đồng thời với các chương trình khác của MIA.Nails & Cafe \n" +
                "- Không áp dụng phụ thu \n" +
                "- Ưu đãi chưa bao gồm VAT \n" +
                "- Khách hàng được phép đến sớm hoặc muộn hơn 15 phút so với giờ hẹn đến \n" +
                "- Mã giảm giá không có giá trị quy đổi thành tiền mặt ";

        String shop1 = "https://cdn.jamja.vn/blog/wp-content/uploads/2019/01/4RAU-Barber-SHOP.jpg";
        String shop2 = "https://cdn.jamja.vn/blog/wp-content/uploads/2019/01/Tiem-Barber-Shop-Vu-Tri.jpg";
        String shop3 = "https://cdn.jamja.vn/blog/wp-content/uploads/2019/01/Tony-Barber-House.jpg";
        String shop4 = "https://cdn.jamja.vn/blog/wp-content/uploads/2019/01/Tiem-Barber-Shop-Vu-Tri-2.jpg";

        salonList = new ArrayList<>();

        salonList.add(new Salon("Barber Shop Vũ Trí","Giảm 30% dịch vụ cắt tóc", "69 Trần Duy Hưng, HN", des1, shop2, "30%",4.7));
        salonList.add(new Salon("Tony Barber","Giảm 10% dịch vụ cắt tóc", "1050 Nguyễn Oanh, HCM", des1, shop3, "10%",4.8));
        salonList.add(new Salon("Paris Hair Salon","Giảm 20% dịch vụ cắt tóc", "123 Gò Vấp, HCM", des1, shop4, "20%",4.9));
        salonList.add(new Salon("4RAU Barber Shop","Giảm 50% dịch vụ cắt tóc", "509 Quang Trung, HCM", des1, shop1, "50%",4.5));

        // recycler view for recent sale
        RecyclerView recyclerView = findViewById(R.id.recycler_view_salon);
        RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this, salonList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setAdapter(viewAdapter);

        // recycler view for newest sale
        RecyclerView recyclerViewNewest = findViewById(R.id.recycler_view_newest);
        RecyclerViewNewestAdapter viewNewestAdapter = new RecyclerViewNewestAdapter(this, salonList);
        recyclerViewNewest.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewNewest.setAdapter(viewNewestAdapter);
        // recycler view for best salon
        RecyclerView recyclerViewBestSalon = findViewById(R.id.recycler_view_best_salon);

        recyclerViewBestSalon.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewBestSalon.setAdapter(viewNewestAdapter);
        salonServiceList = new ArrayList<>();
        salonServiceList.add(new SalonService("Gần Đây", R.drawable.placeholder));
        salonServiceList.add(new SalonService("Gội Đầu", R.drawable.hair_wash));
        salonServiceList.add(new SalonService("Đắp mặt nạ", R.drawable.beauty1));
        salonServiceList.add(new SalonService("Tấy tóc", R.drawable.glue));
        salonServiceList.add(new SalonService("Kid combo", R.drawable.kid));
        salonServiceList.add(new SalonService("Uốn cao cấp", R.drawable.hair_curl));
        salonServiceList.add(new SalonService("Nhuộm Echosline", R.drawable.dye));
        //recycler view for service
        RecyclerView recyclerViewService = findViewById(R.id.recycler_view_service);
        RecyclerViewServiceAdapter recyclerViewServiceAdapter = new RecyclerViewServiceAdapter(this, salonServiceList);
        recyclerViewService.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewService.setAdapter(recyclerViewServiceAdapter);


        //setup tab layout
//        TabLayout tabLayout = findViewById(R.id.tabLayout);
//        tabLayout.addTab(tabLayout.newTab().setText("Dạo Xem"));
//        tabLayout.addTab(tabLayout.newTab().setText("Đặt chỗ"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = findViewById(R.id.pager);
//        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.getCurrentItem();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }
//
    public void clickToRedirectToLogin(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
