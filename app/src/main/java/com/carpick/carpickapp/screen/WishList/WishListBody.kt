package com.carpick.carpickapp.screen.WishList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.R
import com.carpick.carpickapp.model.CarDetailTestModel
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.skydoves.landscapist.glide.GlideImage
import java.text.DecimalFormat

@Composable
fun WishListBody(
    wishlistIds: List<Int>,
    wishlistCars: List<RecommendedCar>,
    carList: List<CarDetailTestModel>,
    onPressCarItem: (idx: Int) -> Unit,
    dataReceived: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F6)),

    ) {
        if(wishlistIds.size > 0) {
            WishListBodyListView(
                carList,
                wishlistCars,
                onPressCarItem
            )
        }
        else if(dataReceived) {
            WishListEmptyBody()
        }

    }
}

@Composable
fun WishListEmptyBody() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "앗!",
            fontSize = 32.sp,
            color = popupBackground,
            fontWeight = FontWeight(700)
        )

        Text(
            text = "위시리스트가 비어있어요.\n추천 받은 차를 위시리스트에\n담아보세요",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color(0xFF9898B7),
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.img_empty),
            contentDescription = "빈 위시리스트",
            modifier = Modifier
                .width(124.dp)
                .height(150.dp)
                .padding(0.dp, 0.dp, 0.dp, 32.dp)
        )

        WishListBodyTestBtn()
    }
}

@Composable
fun WishListBodyTestBtn() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(80.dp, 0.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = popupBackground
            ),
            shape = RoundedCornerShape(99.dp)
        ) {
            Text(
                text = "테스트 다시하기",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight(700)
            )
        }
    }
}

@Composable
fun WishListBodyListView(
    carList: List<CarDetailTestModel>,
    wishlistCars: List<RecommendedCar>,
    onPressCarItem: (idx: Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${carList.size}개의 저장된 차가 있어요.",
            fontSize = 12.sp,
            color = Color(0xFF7A7AA2),
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(0.dp, 24.dp, 0.dp, 0.dp)
        )
        LazyColumn {
            itemsIndexed(wishlistCars) {index, item ->
                WishListCarItem(
                    item,
                    index === carList.size-1,
                    onPressCarItem
                )
            }
        }
    }
}

@Composable
fun WishListCarItem(
    itemData: RecommendedCar,
    isLastIdx: Boolean,
    onPressCarItem: (idx: Int) -> Unit
) {
    val convertPrice = itemData.price/10000
    val dec = DecimalFormat("#,###만원")
    val paddingBottom = if(isLastIdx) 16 else 0
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 16.dp, 24.dp, paddingBottom.dp)
            .clickable {
                onPressCarItem(itemData.id)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(24.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = itemData.modelName,
                    fontSize = 16.sp,
                    color = popupBackground,
                    fontWeight = FontWeight(700)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_favorite_on),
                    contentDescription = "favorite",
                    modifier = Modifier.size(20.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(0.dp, 6.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.width(124.dp)
                ) {
                    Text(
                        text = "${itemData.detailModelName}\n${itemData.trimName}",
                        fontSize = 14.sp,
                        color = Color(0xFF9898B7),
                        fontWeight = FontWeight(500)
                    )

                    Text(
                        text = dec.format(convertPrice),
                        fontSize = 16.sp,
                        color = Color(0xFF3872FF),
                        fontWeight = FontWeight(700),
                        modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp)
                    )
                }

                GlideImage(
                    imageModel = itemData.carImageUrl,
                    modifier = Modifier
                        .width(118.dp)
                        .height(60.dp)
                )
            }
        }
    }
}