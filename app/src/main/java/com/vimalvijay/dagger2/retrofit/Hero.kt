package com.vimalvijay.dagger2.retrofit
import com.google.gson.annotations.SerializedName


class Hero : ArrayList<Hero.HeroItem>(){

    data class HeroItem(
        @SerializedName("bio")
        var bio: String = "", // Thor's father Odin decides his son needed to be taught humility and consequently places Thor (without memories of godhood) into the body and memories of an existing, partially disabled human medical student, Donald Blake.[52] After becoming a doctor and on vacation in Norway, Blake witnesses the arrival of an alien scouting party. Blake flees from the aliens into a cave. After discovering Thor's hammer Mjolnir (disguised as a walking stick) and striking it against a rock, he transforms into the thunder god.[53] Later, in Thor #159, Blake is revealed to have always been Thor, Odin's enchantment having caused him to forget his history as The Thunder God and believe himself mortal.[54]
        @SerializedName("createdby")
        var createdby: String = "", // Stan Lee
        @SerializedName("firstappearance")
        var firstappearance: String = "", // 1962
        @SerializedName("imageurl")
        var imageurl: String = "", // https://www.simplifiedcoding.net/demos/marvel/thor.jpg
        @SerializedName("name")
        var name: String = "", // Thor
        @SerializedName("publisher")
        var publisher: String = "", // Marvel Comics
        @SerializedName("realname")
        var realname: String = "", // Thor Odinson
        @SerializedName("team")
        var team: String = "" // Avengers
    )
}