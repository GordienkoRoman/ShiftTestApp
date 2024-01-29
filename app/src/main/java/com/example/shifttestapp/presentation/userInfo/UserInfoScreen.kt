package com.example.shifttestapp.presentation.userInfo

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.SubcomposeAsyncImage
import com.example.shifttestapp.domain.model.User


@Composable
fun UserInfoScreen(user: User) {
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(
            modifier = Modifier
                .size(128.dp)
        ) {
            SubcomposeAsyncImage(
                model = user.picture,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        UserInfoItem(option = "Name", value = user.name)
        UserInfoItem(option = "Gender", value = user.gender)
        UserInfoItem(option = "Age", value = user.age.toString())
        UserInfoItem(option = "mail", value = user.mail, true) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // Only email apps handle this.
                putExtra(Intent.EXTRA_SUBJECT, "Hi ${user.name},")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(user.mail))
            }
            startActivity(context, intent, null)
        }
        UserInfoItem(option = "number", value = user.number, true) {
            val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${user.number}"))
            startActivity(context, call, null)
        }
        UserInfoItem(option = "Country", value = user.country)
        UserInfoItem(option = "State", value = user.state)
        UserInfoItem(option = "City", value = user.city, true)
        {
            //Не правильно показывает, мб данные некорректные на сайте.
            val gmmIntentUri =
                Uri.parse("geo:${user.coordinates.latitude},${user.coordinates.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(context, mapIntent, null)
        }
    }
}


@Composable
fun UserInfoItem(
    option: String,
    value: String,
    isActive: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    )
    {
        Text(
            text = "$option:",
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            modifier = Modifier.clickable { onClick() },
            text = value,
            color = if (isActive) MaterialTheme.colorScheme.tertiary
            else MaterialTheme.colorScheme.secondary
        )
    }
}