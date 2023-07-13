package com.makescreenshot.deletethis.data.response

import com.google.gson.annotations.SerializedName
import com.makescreenshot.deletethis.presentation.utils.comparator.IdEntity

data class PhotosListData(
    var id: Int,
    @SerializedName("path") var path: String,
) : IdEntity(id)
