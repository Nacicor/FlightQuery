package com.example.flightQuery.main.navigationbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.flightQuery.R

@Composable
fun MyNavigationBar(
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items: List<MyBottomNavigationItem> = listOf(
        MyBottomNavigationItem(
            title = "航班",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.flight_24dp_fill0_wght400_grad0_opsz24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.flight_24dp_fill0_wght400_grad0_opsz24__1_),
            hasNews = false
        ),
        MyBottomNavigationItem(
            title = "匯率",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.attach_money_24dp_fill0_wght400_grad0_opsz24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.attach_money_24dp_fill0_wght400_grad0_opsz24__1_),
            hasNews = false,
        ),
    )
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .padding(8.dp)
    ) {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        onItemSelected(index) // Call the callback function to update selectedItemIndex
                    },
                    label = {
                        Text(text = item.title)
                    },
                    alwaysShowLabel = true,
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount != null) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if (item.hasNews) {
                                    Badge()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title,
                            )
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}




