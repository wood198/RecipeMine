package com.ashley_laptop.recipemine.models

import com.google.gson.annotations.SerializedName

class Recipe private constructor(
    @field:SerializedName("id") val mId: Int,
    @field:SerializedName("title") val mTitle: String,
    @field:SerializedName("readyInMinutes") val mReadyInMinutes: Int,
    @field:SerializedName("servings") val mServings: Int,
    @field:SerializedName("image") val mImage: String,
    @field:SerializedName("expires") val mExpires: Int,
    @field:SerializedName("instructions") val mInstructions: String,
    @field:SerializedName("sourceUrl") val mSourceUrl: String
) {
}
