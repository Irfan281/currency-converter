package com.irfan.currencyapp.api

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: CurrencyData,

	@field:SerializedName("meta")
	val meta: Meta
)

data class Meta(

	@field:SerializedName("last_updated_at")
	val lastUpdatedAt: String
)

data class CurrencyData(

	@field:SerializedName("SGD")
	val sGD: SGD,

	@field:SerializedName("JPY")
	val jPY: JPY,

	@field:SerializedName("EUR")
	val eUR: EUR,

	@field:SerializedName("SAR")
	val sAR: SAR,

	@field:SerializedName("IDR")
	val iDR: IDR,

	@field:SerializedName("MYR")
	val mYR: MYR
)

data class IDR(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("value")
	val value: Double
)

data class JPY(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("value")
	val value: Double
)

data class EUR(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("value")
	val value: Double
)

data class SAR(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("value")
	val value: Double
)

data class SGD(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("value")
	val value: Double
)

data class MYR(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("value")
	val value: Double
)
