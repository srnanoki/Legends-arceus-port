package com.arceusmobile

import android.content.Intent
import android.net.Uri

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.platform.LocalContext

private val Purple = Color(0xFFB78AF7)
private val Deep = Color(0xFF100D18)
private val Panel = Color(0xFF1D1828)
private val PanelAlt = Color(0xFF292137)
private val Sidebar = Color(0xFF171220)
private val Muted = Color(0xFFB9ADC8)
private val Good = Color(0xFF8FE0B0)

@Composable
fun ArceusApp() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val prefs = remember { context.getSharedPreferences("arceus_prefs", 0) }

    var selectedGame by remember { mutableStateOf(prefs.getString("game_uri", null)) }
    var selectedKeys by remember { mutableStateOf(prefs.getString("keys_uri", null)) }
    var selectedDriver by remember { mutableStateOf(prefs.getString("driver_uri", null)) }
    var tab by remember { mutableStateOf("Início") }
    var resolution by remember { mutableFloatStateOf(prefs.getFloat("resolution", 0.5f)) }
    var fxaa by remember { mutableStateOf(prefs.getBoolean("fxaa", false)) }
    var shaderCache by remember { mutableStateOf(prefs.getBoolean("shader_cache", true)) }
    var fps by remember { mutableStateOf(prefs.getString("fps", "30 FPS") ?: "30 FPS") }
    var status by remember { mutableStateOf("Interface pronta. O motor de execução ainda não está integrado.") }

    fun persistUri(key: String, uri: Uri) {
        try { context.contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION) } catch (_: Exception) {}
        prefs.edit().putString(key, uri.toString()).apply()
    }

    val gamePicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { persistUri("game_uri", it); selectedGame = it.toString(); status = "Arquivo do jogo selecionado." }
    }
    val keysPicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { persistUri("keys_uri", it); selectedKeys = it.toString(); status = "Arquivo prod.keys selecionado localmente." }
    }
    val driverPicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { persistUri("driver_uri", it); selectedDriver = it.toString(); status = "Pacote de driver selecionado." }
    }

    MaterialTheme(colorScheme = darkColorScheme(
        primary = Purple, onPrimary = Color(0xFF211333), background = Deep,
        surface = Panel, onBackground = Color.White, onSurface = Color.White,
        secondary = Color(0xFFE69AD8), onSurfaceVariant = Muted
    )) {
        Row(Modifier.fillMaxSize().background(Deep)) {
            Column(
                Modifier.width(196.dp).fillMaxHeight().background(Sidebar).padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.SportsEsports, null, tint = Purple, modifier = Modifier.size(27.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("ARCEUS", fontWeight = FontWeight.Black, fontSize = 20.sp)
                    }
                    Text("MOBILE  •  0.2", color = Muted, fontSize = 11.sp)
                    Spacer(Modifier.height(12.dp))
                    NavItem("Início", tab == "Início", Icons.Default.Home) { tab = "Início" }
                    NavItem("Configurações", tab == "Configurações", Icons.Default.Tune) { tab = "Configurações" }
                    NavItem("Preparação", tab == "Preparação", Icons.Default.Build) { tab = "Preparação" }
                    NavItem("Conexão PC", tab == "Conexão PC", Icons.Default.Computer) { tab = "Conexão PC" }
                }
                Text("Projeto independente\nFeito para Legends: Arceus", color = Muted, fontSize = 10.sp)
            }

            Column(
                Modifier.weight(1f).fillMaxHeight().verticalScroll(rememberScrollState()).padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(tab, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                        Text("Pokémon Legends: Arceus  •  Snapdragon 685", color = Muted, fontSize = 12.sp)
                    }
                    Surface(color = Color(0xFF263B31), shape = RoundedCornerShape(50)) {
                        Text("BUILD 0.2", Modifier.padding(horizontal = 13.dp, vertical = 7.dp), color = Good, fontSize = 11.sp)
                    }
                }

                when (tab) {
                    "Início" -> {
                        CardBlock {
                            Image(
                                painter = painterResource(id = R.drawable.arceus_art),
                                contentDescription = "Arte ilustrada de Pokémon Legends: Arceus",
                                modifier = Modifier.fillMaxWidth().height(205.dp).clip(RoundedCornerShape(14.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Text("A aventura começa aqui.", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text("Central de preparação para Pokémon Legends: Arceus.", color = Muted)
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Button(onClick = { tab = "Preparação" }) {
                                    Icon(Icons.Default.RocketLaunch, null); Spacer(Modifier.width(7.dp)); Text("Preparar jogo")
                                }
                                OutlinedButton(onClick = { tab = "Configurações" }) {
                                    Icon(Icons.Default.Tune, null); Spacer(Modifier.width(7.dp)); Text("Configurar")
                                }
                            }
                        }
                        CardBlock {
                            Text("Seus arquivos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            InfoLine("Jogo", fileLabel(selectedGame))
                            InfoLine("prod.keys", fileLabel(selectedKeys))
                            InfoLine("Driver", fileLabel(selectedDriver))
                        }
                        CardBlock {
                            Text("Estado do projeto", fontWeight = FontWeight.Bold)
                            Text(status, color = Muted)
                            Text("Esta versão organiza os arquivos e as preferências. A execução do jogo depende da integração do motor.", color = Color(0xFFFFD28A), fontSize = 12.sp)
                        }
                    }
                    "Configurações" -> {
                        CardBlock {
                            Text("Gráficos", fontWeight = FontWeight.Bold, fontSize = 19.sp)
                            Text("Resolução interna: ${(resolution * 100).toInt()}%", color = Muted)
                            Slider(value = resolution, onValueChange = {
                                resolution = it; prefs.edit().putFloat("resolution", it).apply()
                            }, valueRange = 0.25f..1f)
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("0,25x", color = Muted); Text("1,0x", color = Muted)
                            }
                            Divider(color = Color(0xFF514064))
                            SettingSwitch("FXAA", "Suavização de serrilhado (quando suportada)", fxaa) {
                                fxaa = it; prefs.edit().putBoolean("fxaa", it).apply()
                            }
                            SettingSwitch("Cache de shaders", "Guardar shaders compilados", shaderCache) {
                                shaderCache = it; prefs.edit().putBoolean("shader_cache", it).apply()
                            }
                            Text("Renderizador Vulkan — depende do motor integrado.", color = Muted, fontSize = 12.sp)
                        }
                        CardBlock {
                            Text("Limite de quadros", fontWeight = FontWeight.Bold)
                            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                                listOf("30 FPS", "60 FPS", "Sem limite").forEach {
                                    FilterChip(selected = fps == it, onClick = {
                                        fps = it; prefs.edit().putString("fps", it).apply()
                                    }, label = { Text(it) })
                                }
                            }
                            Text("As opções serão aplicadas quando o runtime oferecer suporte.", color = Muted, fontSize = 11.sp)
                        }
                    }
                    "Preparação" -> {
                        CardBlock {
                            Text("Preparação em 3 etapas", fontSize = 21.sp, fontWeight = FontWeight.Bold)
                            Text("Selecione os arquivos que o motor precisará. Tudo permanece no seu dispositivo.", color = Muted)
                        }
                        StepCard("01", "Storage / jogo", "Selecione o arquivo do jogo (XCI ou NSP).", selectedGame, "Selecionar jogo") {
                            gamePicker.launch(arrayOf("*/*"))
                        }
                        StepCard("02", "prod.keys", "Selecione seu arquivo de chaves. Ele não será enviado pelo app.", selectedKeys, "Selecionar prod.keys") {
                            keysPicker.launch(arrayOf("*/*"))
                        }
                        StepCard("03", "Driver gráfico", "Selecione um pacote de driver compatível, se necessário.", selectedDriver, "Selecionar driver") {
                            driverPicker.launch(arrayOf("*/*"))
                        }
                        Button(
                            onClick = { status = if (selectedGame != null && selectedKeys != null) "Arquivos selecionados. A validação técnica depende da integração do motor." else "Faltam arquivos obrigatórios: jogo e prod.keys." },
                            modifier = Modifier.fillMaxWidth()
                        ) { Text("Conferir arquivos selecionados") }
                        Text("A seleção não confirma compatibilidade nem valida o conteúdo das chaves; isso será feito pelo runtime.", color = Muted, fontSize = 11.sp)
                    }
                    "Conexão PC" -> {
                        CardBlock {
                            Text("PC Worker", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text("A preparação pesada poderá ser feita por um computador confiável na rede local.", color = Muted)
                            var host by remember { mutableStateOf(prefs.getString("worker_host", "http://192.168.0.10:8765") ?: "") }
                            OutlinedTextField(value = host, onValueChange = { host = it }, label = { Text("Endereço do PC na rede local") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                            Button(onClick = {
                                prefs.edit().putString("worker_host", host).apply()
                                status = "Endereço salvo. O serviço PC Worker ainda não está conectado."
                            }) { Text("Salvar endereço") }
                            Text("Não compartilhe prod.keys com computadores ou pessoas não confiáveis.", color = Color(0xFFFFD28A), fontSize = 12.sp)
                        }
                    }
                }
                Text("ARCEUS Mobile é um projeto independente e não é afiliado à Nintendo.", color = Muted, fontSize = 10.sp)
            }
        }
    }
}

private fun fileLabel(uri: String?): String =
    uri?.let { Uri.parse(it).lastPathSegment?.substringAfterLast('/') ?: "Selecionado" } ?: "Não selecionado"

@Composable
private fun StepCard(number: String, title: String, description: String, uri: String?, action: String, onClick: () -> Unit) {
    CardBlock {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(color = PanelAlt, shape = RoundedCornerShape(12.dp)) {
                Text(number, Modifier.padding(horizontal = 13.dp, vertical = 9.dp), color = Purple, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(description, color = Muted, fontSize = 12.sp)
            }
            Text(if (uri == null) "Pendente" else "Selecionado", color = if (uri == null) Color(0xFFFFD28A) else Good, fontSize = 11.sp)
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(uri?.let { fileLabel(it) } ?: "Nenhum arquivo escolhido", color = Muted, fontSize = 11.sp, modifier = Modifier.weight(1f))
            OutlinedButton(onClick = onClick) { Text(action) }
        }
    }
}

@Composable
private fun NavItem(label: String, selected: Boolean, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Surface(color = if (selected) Color(0xFF3B2A56) else Color.Transparent, shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Row(Modifier.padding(horizontal = 11.dp, vertical = 11.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = if (selected) Purple else Muted)
            Spacer(Modifier.width(10.dp))
            Text(label, color = if (selected) Color.White else Muted, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
        }
    }
}

@Composable
private fun CardBlock(content: @Composable ColumnScope.() -> Unit) {
    Surface(color = Panel, shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp), content = content)
    }
}

@Composable
private fun InfoLine(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Muted, fontSize = 12.sp)
        Text(value, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun SettingSwitch(title: String, subtitle: String, checked: Boolean, onChecked: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium)
            Text(subtitle, color = Muted, fontSize = 11.sp)
        }
        Switch(checked = checked, onCheckedChange = onChecked)
    }
}

