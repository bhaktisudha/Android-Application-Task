package com.bhaktisudha.androidapplicationtask.resultModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class ResultModelItem(
    @PrimaryKey(autoGenerate = true)
    var resultId : Int? = null,
    var accountId: String? = null,
    var carrier: String? = null,
    var dob: String? = null,
    var expirationDate: String? = null,
    var firstName: String? = null,
    var id: String? = null,
    var lastName: String? = null,
    var licenseNumber: String? = null,
    var state: String? = null,
    var status: String? = null
)