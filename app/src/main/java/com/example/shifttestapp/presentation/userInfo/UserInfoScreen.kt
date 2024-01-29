package com.example.shifttestapp.presentation.userInfo

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.SubcomposeAsyncImage
import com.example.shifttestapp.domain.model.User

@Composable
fun UserInfoScreen(user: User){
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {

        Row {
            Box(
                modifier = Modifier
                    .size(72.dp)
            ) {
                SubcomposeAsyncImage(
                    model = user.picture,
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(text = user.name)
                Button(onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:") // Only email apps handle this.
                        putExtra(Intent.EXTRA_SUBJECT, "Hi ${user.name},")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(user.mail))
                    }
                    ContextCompat.startActivity(context, intent, null)
                }) {
                    Text(text = user.mail, color = Color.Yellow)
                }
                Button(
                    onClick = {
                        val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${user.number}"))
                        ContextCompat.startActivity(context, call, null)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        user.number,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}