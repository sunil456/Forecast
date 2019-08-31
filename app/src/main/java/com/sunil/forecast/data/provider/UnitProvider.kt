package com.sunil.forecast.data.provider

import com.sunil.forecast.internal.UnitSystem

interface UnitProvider
{
    fun getUnitSystem() : UnitSystem
}