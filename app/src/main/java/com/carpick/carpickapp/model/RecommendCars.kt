package com.carpick.carpickapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecommendCars(
    @SerializedName("recommended_cars")
    val recommendCars: List<RecommendedCar>
):Serializable

data class RecommendedCar(
    @SerializedName("air_conditioning_option_description")
    val airConditioningOptionDescription: String,
    @SerializedName("audio_and_visual_option_description")
    val audioAndVisualOptionDescription: String,
    @SerializedName("car_body_type_name")
    val carBodyTypeName: String,
    @SerializedName("car_brand_name")
    val carBrandName: String,
    @SerializedName("car_image_url")
    val carImageUrl: String,
    @SerializedName("city_fuel_economy")
    val cityFuelEconomy: Double,
    @SerializedName("combined_fuel_economy")
    val combinedFuelEconomy: Double,
    @SerializedName("convenience_option_description")
    val convenienceOptionDescription: String,
    @SerializedName("detail_model_name")
    val detailModelName: String,
    val displacement: Int,
    @SerializedName("engine_cylinder_count")
    val engineCylinderCount: Int,
    @SerializedName("engine_type_name")
    val engineTypeName: String,
    @SerializedName("external_option_description")
    val externalOptionDescription: String,
    @SerializedName("front_brake_type_name")
    val frontBrakeTypeName: String,
    @SerializedName("front_suspension_type_name")
    val frontSuspensionTypeName: String,
    @SerializedName("fuel_economy")
    val fuelEconomy: Double,
    @SerializedName("fuel_type_name")
    val fuelTypeName: String,
    val height: Int,
    @SerializedName("highway_fuel_economy")
    val highwayFuelEconomy: Double,
    val id: Int,
    @SerializedName("internal_option_description")
    val internalOptionDescription: String,
    val length: Int,
    @SerializedName("maximum_power_description")
    val maximumPowerDescription: String,
    @SerializedName("maximum_torque_description")
    val maximumTorqueDescription: String,
    @SerializedName("model_name")
    val modelName: String,
    @SerializedName("number_of_gears")
    val numberOfGears: Int,
    val price: Int,
    @SerializedName("rear_brake_type_name")
    val rearBrakeTypeName: String,
    @SerializedName("rear_suspension_type_name")
    val rearSuspensionTypeName: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("security_option_description")
    val securityOptionDescription: String,
    @SerializedName("sheet_option_description")
    val sheetOptionDescription: String,
    val tags: List<Tag>,
    @SerializedName("transmission_type_name")
    val transmissionTypeName: String,
    @SerializedName("trim_name")
    val trimName: String,
    val weight: Int,
    val wheelbase: Int,
    val width: Int,
    @SerializedName("zero_to_hundred")
    val zeroToHundred: Double
): Serializable

data class Tag(
    @SerializedName("tag_description")
    val tagDescription: String,
    @SerializedName("tag_name")
    val tagName: String,
    @SerializedName("tag_rgb_color_code")
    val tagRgbColorCode: String
): Serializable
