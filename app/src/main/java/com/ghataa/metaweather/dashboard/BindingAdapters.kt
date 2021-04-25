package com.ghataa.metaweather.dashboard

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ghataa.metaweather.data.model.WeatherInfo
import com.ghataa.metaweather.data.source.remote.MainWebservice.Factory.API_ICON_URL

@BindingAdapter("app:src")
fun bindWeatherIcon(imageView: ImageView, items: List<WeatherInfo>) {
    if (items.isEmpty()) return

    Glide.with(imageView)
        .load("$API_ICON_URL${items[0].weatherStateAbbr}.png")
        .into(imageView)
}
