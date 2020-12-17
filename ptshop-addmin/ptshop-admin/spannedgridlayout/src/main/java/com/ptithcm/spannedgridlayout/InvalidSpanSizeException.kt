package com.n16dccn159.spannedgridlayout

class InvalidSpanSizeException(errorSize: Int, maxSpanSize: Int) :
    RuntimeException("Invalid item span size: $errorSize. Span size must be in the range: (1...$maxSpanSize)")