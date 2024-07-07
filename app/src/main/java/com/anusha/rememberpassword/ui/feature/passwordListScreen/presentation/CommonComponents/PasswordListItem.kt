

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anusha.rememberpassword.R


@Composable
fun PasswordListItem(passwordProvider:String,onArrowIconClick: () -> Unit) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = passwordProvider,
                    modifier = Modifier,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 20.sp)
                )
                Text(
                    text = "******",
                    modifier = Modifier,
                    textAlign = TextAlign.Start
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                tint = Color.Black,
                contentDescription = "",
                modifier = Modifier.clickable {
                    onArrowIconClick()
                }
            )

        }

}