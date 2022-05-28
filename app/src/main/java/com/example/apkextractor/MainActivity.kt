package com.example.apkextractor

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apkextractor.ui.theme.ApkExtractorTheme

const val TAG = "MainActivityTAG"

class MainActivity : ComponentActivity() {
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        setContent {
            ApkExtractorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.onBackground
                ) {
                    PackageList(packageManager, packages)
                }
            }
        }
    }
}

@Composable
fun PackageList(packageManager: PackageManager, packages: List<ApplicationInfo>) {
    LazyColumn {
        itemsIndexed(packages) { _, item ->
            val packageName = item.packageName
            val appNameNotYet = packageManager.getApplicationInfo(packageName, 0)
            val appName = packageManager.getApplicationLabel(appNameNotYet).toString()
            PackageItem(appName, packageName)
        }
    }

}

@Composable
fun PackageItem(appName: String, packageName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(appName)
            Text(packageName)
        }
        Text(
            "Extract",
            modifier = Modifier
                .padding(end = 16.dp)
        )
    }
    Divider(
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApkExtractorTheme {
        PackageItem("App Name", "Package Name")
    }
}


// Psuedo Code