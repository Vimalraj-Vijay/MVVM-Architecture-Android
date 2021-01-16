package com.vimalvijay.dagger2.main.model

import com.google.gson.annotations.SerializedName


class Hero : ArrayList<Hero.HeroItem>() {

    data class HeroItem(
        @SerializedName("bio")
        var bio: String = "",
        @SerializedName("createdby")
        var createdby: String = "",
        @SerializedName("firstappearance")
        var firstappearance: String = "",
        @SerializedName("imageurl")
        var imageurl: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("publisher")
        var publisher: String = "",
        @SerializedName("realname")
        var realname: String = "",
        @SerializedName("team")
        var team: String = ""
    )
}