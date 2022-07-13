package com.example.innoctive_test.viewmodel.model

import com.google.gson.annotations.SerializedName
//data class DefaultModel(val url: String, val width: Int,val height: Int, val categories: CategoriesBean)

class DefaultModel {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("width")
    var width = 0

    @SerializedName("height")
    var height = 0

    @SerializedName("breeds")
    var breeds: List<*>? = null

    /**
     * id : 5
     * name : boxes
     */
    @SerializedName("categories")
    var categories: List<CategoriesBean>? = null

    class CategoriesBean {
        @SerializedName("id")
        var id = 0

        @SerializedName("name")
        var name: String? = null
    }
}
