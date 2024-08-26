package com.devpro.android55_day7.models.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryObj {

@SerializedName("slug")
@Expose
private String slug;
@SerializedName("name")
@Expose
private String name;
@SerializedName("url")
@Expose
private String url;

public String getSlug() {
return slug;
}

public void setSlug(String slug) {
this.slug = slug;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

}