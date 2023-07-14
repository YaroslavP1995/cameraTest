package com.makescreenshot.deletethis.data.network.response

import com.google.gson.annotations.SerializedName
import com.makescreenshot.deletethis.presentation.utils.comparator.IdEntity

data class PhotosListData(
    var id: String,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("blur_hash") var blurHash: String? = null,
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("urls") var urls: Urls? = Urls(),
) : IdEntity(id)
