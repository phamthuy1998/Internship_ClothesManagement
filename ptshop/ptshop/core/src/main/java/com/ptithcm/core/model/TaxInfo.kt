package com.ptithcm.core.model

data class TaxInfo(
    val tax_lines: List<TaxLine>?,
    val taxes_included: Boolean?,
    val total_tax: String?
)