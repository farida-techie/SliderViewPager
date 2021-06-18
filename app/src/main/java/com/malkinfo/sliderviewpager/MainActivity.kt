package com.malkinfo.sliderviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    /**set Image*/
    private lateinit var sliderItemList:ArrayList<SliderItem>
    private lateinit var sliderAdapter:SliderAdapter
    private lateinit var sliderHandle:Handler
    private lateinit var sliderRun :Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sliderItems()
        itemSliderView()
    }

    private fun sliderItems() {
        sliderItemList = ArrayList()
        sliderAdapter = SliderAdapter(viewPagerImgSlider, sliderItemList)
        viewPagerImgSlider.adapter = sliderAdapter
        viewPagerImgSlider.clipToPadding = false
        viewPagerImgSlider.clipChildren = false
        viewPagerImgSlider.offscreenPageLimit = 3
        viewPagerImgSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPagerImgSlider.setPageTransformer(comPosPageTarn)
        sliderHandle = Handler()
        sliderRun = Runnable {
            viewPagerImgSlider.currentItem = viewPagerImgSlider.currentItem + 1
        }

        viewPagerImgSlider.registerOnPageChangeCallback(
                object :ViewPager2.OnPageChangeCallback(){

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        sliderHandle.removeCallbacks(sliderRun)
                        sliderHandle.postDelayed(sliderRun,2000)
                    }
                })

    }

    override fun onPause() {
        super.onPause()
        sliderHandle.removeCallbacks(sliderRun)

    }


    override fun onResume() {
        super.onResume()
        sliderHandle.postDelayed(sliderRun,2000)
    }

    private fun itemSliderView() {
        sliderItemList.add(SliderItem(R.drawable.slid1))
        sliderItemList.add(SliderItem(R.drawable.slid2))
        sliderItemList.add(SliderItem(R.drawable.sli3))
        sliderItemList.add(SliderItem(R.drawable.sli4))
        sliderItemList.add(SliderItem(R.drawable.sli5))
        sliderItemList.add(SliderItem(R.drawable.sli6))
        sliderItemList.add(SliderItem(R.drawable.sli7))
        sliderItemList.add(SliderItem(R.drawable.sli8))
        sliderItemList.add(SliderItem(R.drawable.sli9))
        sliderItemList.add(SliderItem(R.drawable.sli10))
        sliderItemList.add(SliderItem(R.drawable.sli11))
    }


}