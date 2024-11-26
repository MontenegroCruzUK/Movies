package com.montenegro.movies.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.montenegro.movies.R
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.graphics.StrokeCap

@Composable
fun MyConstraintLayout() {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .padding(top = statusBarHeight) // Añade el padding del statusBar
    ) {
        val (bannerBox, movieTitleBox, progressBarBox, menuBox) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp) // Altura del banner aumentada
                .constrainAs(bannerBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Escala para llenar el ancho y largo de la caja
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color.Red.copy(alpha = 0.7f), Color.Transparent),
                            startX = 0f,
                            endX = 500f // Ajusta este valor según sea necesario
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .size(130.dp) // Tamaño de la carátula
                    .align(Alignment.CenterStart) // Centrar verticalmente y alinear a la izquierda
            ) {
                Image(
                    painter = painterResource(id = R.drawable.movie_cover), // Reemplaza con tu imagen de la carátula
                    contentDescription = null,
                    contentScale = ContentScale.Fit, // Ajusta la imagen dentro del Box
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red) // Fondo rojo intenso
                .constrainAs(movieTitleBox) {
                    top.linkTo(bannerBox.bottom) // Sin margen
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = "Smile 2 (2024)",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White, // Texto en blanco
                    fontWeight = FontWeight.Bold // Texto en negrita
                ),
                modifier = Modifier
                    .padding(8.dp) // Margen interno para el texto
                    .align(Alignment.CenterHorizontally) // Centrar el texto
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp) // Margen interno para el contenido
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f) // Ocupa el espacio disponible en el lado izquierdo
                ) {
                    Box(
                        contentAlignment = Alignment.Center, // Centra el contenido dentro del Box
                        modifier = Modifier.size(50.dp) // Tamaño original
                    ) {
                        CircularProgressIndicator(
                            progress = 0.5f, // 50% de progreso
                            color = Color.Yellow, // Cambiado a color amarillo para mejor visibilidad
                            strokeCap = StrokeCap.Round, // Estilo redondeado
                            trackColor = Color.Gray, // Color gris para el resto del círculo
                            modifier = Modifier.fillMaxSize()
                        )

                        Text(
                            text = "50",
                            color = Color.White, // Cambiar a blanco para que se vea sobre el fondo rojo
                            fontWeight = FontWeight.Bold, // Texto en negrita
                            fontSize = 16.sp // Tamaño original
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp)) // Espacio original

                    Text(
                        text = "Puntuación",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White, // Cambiar a blanco para que se vea sobre el fondo rojo
                            fontWeight = FontWeight.Bold, // Texto en negrita
                            fontSize = 16.sp // Tamaño original
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.weight(1f) // Ocupa el espacio disponible en el lado derecho
                ) {
                    // Íconos
                    Image(
                        painter = painterResource(id = R.drawable.ic_info), // Reemplaza con tu ícono de información
                        contentDescription = "Info",
                        modifier = Modifier.size(24.dp) // Tamaño original
                    )

                    Spacer(modifier = Modifier.width(8.dp)) // Espacio original

                    Image(
                        painter = painterResource(id = R.drawable.ic_share), // Reemplaza con tu ícono de compartir
                        contentDescription = "Share",
                        modifier = Modifier.size(24.dp) // Tamaño original
                    )

                    Spacer(modifier = Modifier.width(8.dp)) // Espacio original

                    Image(
                        painter = painterResource(id = R.drawable.ic_like), // Reemplaza con tu ícono de corazón
                        contentDescription = "Like",
                        modifier = Modifier.size(24.dp) // Tamaño original
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // Fondo blanco para la nueva caja del menú
                .constrainAs(menuBox) {
                    top.linkTo(movieTitleBox.bottom) // Coloca debajo de la caja del título y puntuación
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            TabRow(
                selectedTabIndex = 0,
                modifier = Modifier.background(Color.White), // Fondo blanco para el TabRow
                contentColor = Color.Black,
                indicator = {tabPositions ->TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[0]),
                    color=Color.Red
                )  }
            ) { // Usando TabRow para el menú
                Tab(
                    selected = true,
                    onClick = { /* Acción para Actores Principales */ },
                    text = {
                        Text(
                            text = "Actores Principales",
                            color = Color.Black,
                            maxLines = 2, // Permite un máximo de 2 líneas
                            textAlign = TextAlign.Center // Centra el texto
                        )
                    },
                    modifier = Modifier.background(Color.White).weight(1f).padding(horizontal = 8.dp) // Ajusta el ancho y añade espacio a los lados
                )
                Tab(
                    selected = false,
                    onClick = { /* Acción para Actores Secundarios */ },
                    text = {
                        Text(
                            text = "Actores Secundarios",
                            color = Color.Black,
                            maxLines = 2, // Permite un máximo de 2 líneas
                            textAlign = TextAlign.Center // Centra el texto
                        )
                    },
                    modifier = Modifier.background(Color.White).weight(1f).padding(horizontal = 8.dp) // Ajusta el ancho y añade espacio a los lados
                )
                Tab(
                    selected = false,
                    onClick = { /* Acción para Equipo */ },
                    text = {
                        Text(
                            text = "Equipo",
                            color = Color.Black,
                            maxLines = 2, // Permite un máximo de 2 líneas
                            textAlign = TextAlign.Center // Centra el texto
                        )
                    },
                    modifier = Modifier.background(Color.White).weight(1f).padding(horizontal = 8.dp) // Ajusta el ancho y añade espacio a los lados
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyConstraintLayout() {
    MyConstraintLayout()
}
