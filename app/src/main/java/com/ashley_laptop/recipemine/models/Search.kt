package com.ashley_laptop.recipemine.models

import com.google.gson.annotations.SerializedName

class Search private constructor(
    @field:SerializedName("results") val mResults: ArrayList<Recipe>,
    @field:SerializedName("baseUri") val mBaseUri: String,
    @field:SerializedName("offset") val mOffset: Int,
    @field:SerializedName("number") val mNumber: Int,
    @field:SerializedName("totalResults") val mTotalResults: Int,
    @field:SerializedName("processingTimeMs") val mProcessingTimeMs: Int,
    @field:SerializedName("expires") val mExpires: Long
) {
}
