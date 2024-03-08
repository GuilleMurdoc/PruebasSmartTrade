package com.example.kotlinpruebaempty

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.kotlinpruebaempty.ui.theme.KotlinPruebaEmptyTheme
import com.example.kotlinpruebaempty.ProductoDTO

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navControler = rememberNavController()

            NavHost(navController = navControler, startDestination = "main"){
                composable("main"){
                    MyApp {
                        Bars(navControler)
                    }
                }
                composable(
                    route = "detail/{mediaId}",
                    arguments = listOf(
                        navArgument("mediaId") {
                            type = NavType.IntType
                        }
                    )
                ){
                    backStackEntry -> val id = backStackEntry.arguments?.getInt("mediaId")
                    requireNotNull(id)
                    MyApp {
                        detailScreen(id)
                    }
                }
            }
        }
    }
}

@Composable
fun detailScreen(id: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Text(
            text = "Detalles ${id}",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    KotlinPruebaEmptyTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bars(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                     IconButton(onClick = { /*TODO*/ }) {
                         Icon(
                             imageVector = Icons.Default.Menu,
                             contentDescription = null
                         )
                     }
                },
                title = {
                    Text(text = "Nombre de a aplicación")
                }
            )
        }
    ) {
        padding -> MediaList(navController = navController, modifier = Modifier.padding(padding))
    }
}

@Composable
fun State() {
    val (getText, setText) = rememberSaveable{mutableStateOf("Paco")}
    StateSample(getText, setText)
}

@Composable
fun StateSample(value: String, onValueChange: (String) -> Unit) {


    Column(
        modifier = Modifier
            .padding(all = 15.dp)
    ) {
        TextField(
            value = value,
            onValueChange = {onValueChange(it)}
        )

        Text(text = value)

        Button(
            onClick = {onValueChange("")},
            enabled = value.isNotEmpty()
        ){
            Text(text = "Clear")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
     Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun NavigationBarSample() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MediaList(navController: NavController, modifier: Modifier) {
    LazyColumn (
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier,
    ){
        items(100) {
            val p = ProductoDTO(it)

            MediaItem(p, navController)
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MediaItem(p: ProductoDTO, navController: NavController){
    Column(
        modifier = Modifier
            .clickable {
                navController
                    .navigate("detail/${p.id}")
            }
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
                //.background(color = Color.Red)
        ) {
            SubcomposeAsyncImage(
                model = "https://loremflickr.com/640/360\n",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                loading = {
                    CircularProgressIndicator()
                }
            )

            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp),
                tint = Color.White,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(all = 10.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = p.getNombre(),
                color = Color.White
            )
        }

    }
}

//@Preview(showBackground = true, widthDp = 400, heightDp = 200)
@Composable
fun ButtonText() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Click aqui",
            modifier = Modifier
                .clickable { }
                .background(Color.Cyan)
                .border(width = 2.dp, color = Color.Blue)
                .padding(all = 10.dp)                ,
        )
    }
}