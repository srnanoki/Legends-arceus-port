package com.arceusmobile

import org.yuzu.yuzu_emu.R
import org.yuzu.yuzu_emu.NativeLibrary
import org.yuzu.yuzu_emu.model.Game
import android.widget.Toast

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
import androidx.compose.ui.platform.LocalView
import androidx.navigation.findNavController
import org.yuzu.yuzu_emu.HomeNavigationDirections
import org.yuzu.yuzu_emu.features.settings.model.Settings
import org.yuzu.yuzu_emu.features.settings.ui.SettingsSubscreen

private val Purple = Color(0xFFB78AF7)
private val Deep = Color(0xFF100D18)
private val Panel = Color(0xFF1D1828)
private val PanelAlt = Color(0xFF292137)
private val Sidebar = Color(0xFF171220)
private val Muted = Color(0xFFB9ADC8)
private val Good = Color(0xFF8FE0B0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArceusApp() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val navController = LocalView.current.findNavController()
    val prefs = remember { context.getSharedPreferences("arceus_prefs", 0) }

    var selectedGame by remember { mutableStateOf(prefs.getString("game_uri", null)) }
    var selectedKeys by remember { mutableStateOf(prefs.getString("keys_uri", null)) }
    var selectedDriver by remember { mutableStateOf(prefs.getString("driver_uri", null)) }
    var tab by remember { mutableStateOf("Início") }
    var resolution by remember { mutableFloatStateOf(prefs.getFloat("resolution", 0.5f)) }
    var fxaa by remember { mutableStateOf(prefs.getBoolean("fxaa", false)) }
    var shaderCache by remember { mutableStateOf(prefs.getBoolean("shader_cache", true)) }
    var fps by remember { mutableStateOf(prefs.getString("fps", "30 FPS") ?: "30 FPS") }
    var status by remember { mutableStateOf("ARCEUS pronto. Verifique chaves, firmware e jogo antes de iniciar.") }
    val keysReady = remember { runCatching { NativeLibrary.areKeysPresent() }.getOrDefault(false) }
    val firmwareReady = remember { runCatching { NativeLibrary.isFirmwareAvailable() }.getOrDefault(false) }
    val gameReady = !selectedGame.isNullOrBlank()

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
                        Text("ARCEUS", color = Color.White, fontWeight = FontWeight.Black, fontSize = 20.sp)
                    }
                    Text("MOBILE  •  0.1.1", color = Muted, fontSize = 11.sp)
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
                        Text("BUILD 0.1.1", Modifier.padding(horizontal = 13.dp, vertical = 7.dp), color = Good, fontSize = 11.sp)
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
                                Button(
                                    enabled = gameReady && keysReady,
                                    onClick = {
                                        if (selectedGame.isNullOrBlank()) {
                                            tab = "Preparação"
                                        } else {
                                            runCatching {
                                                val uri = Uri.parse(selectedGame)
                                                val title = uri.lastPathSegment?.substringAfterLast('/') ?: "Pokémon Legends: Arceus"
                                                val game = Game(title = title, path = selectedGame!!)
                                                navController.navigate(HomeNavigationDirections.actionGlobalEmulationActivity(game))
                                            }.onFailure {
                                                Toast.makeText(context, "Não foi possível iniciar o jogo: ${it.message}", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                    }
                                ) {
                                    Icon(Icons.Default.PlayArrow, null); Spacer(Modifier.width(7.dp)); Text("Jogar")
                                }
                                OutlinedButton(onClick = { tab = "Preparação" }) {
                                    Icon(Icons.Default.FolderOpen, null); Spacer(Modifier.width(7.dp)); Text("Biblioteca / arquivos")
                                }
                                OutlinedButton(onClick = { tab = "Configurações" }) {
                                    Icon(Icons.Default.Tune, null); Spacer(Modifier.width(7.dp)); Text("Configurar")
                                }
                            }
                            Text(
                                "Chaves: ${if (keysReady) "válidas" else "não detectadas"}  •  Firmware: ${if (firmwareReady) "instalado" else "não detectado"}",
                                color = if (keysReady) Good else Color(0xFFFFD28A),
                                fontSize = 12.sp
                            )
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
                            Text("A validação de chaves e firmware consulta o motor nativo. O jogo selecionado é encaminhado à atividade de emulação do Eden.", color = Muted, fontSize = 12.sp)
                        }
                    }
                    "Configurações" -> {
                        CardBlock {
                            Text("Configurações do emulador", fontWeight = FontWeight.Bold, fontSize = 19.sp)
                            Text(
                                "Abra os painéis nativos do Eden. As alterações feitas neles são salvas na configuração do emulador e aplicadas pelo próprio motor.",
                                color = Muted
                            )
                            Button(
                                onClick = {
                                    val action = HomeNavigationDirections.actionGlobalSettingsActivity(
                                        null, Settings.MenuTag.SECTION_RENDERER
                                    )
                                    navController.navigate(action)
                                }, modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Tune, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Gráficos e renderização")
                            }
                            OutlinedButton(
                                onClick = {
                                    val action = HomeNavigationDirections.actionGlobalSettingsActivity(
                                        null, Settings.MenuTag.SECTION_SYSTEM
                                    )
                                    navController.navigate(action)
                                }, modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Settings, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Sistema e compatibilidade")
                            }
                            OutlinedButton(
                                onClick = {
                                    val action = HomeNavigationDirections.actionGlobalSettingsActivity(
                                        null, Settings.MenuTag.SECTION_AUDIO
                                    )
                                    navController.navigate(action)
                                }, modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.VolumeUp, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Áudio")
                            }
                            OutlinedButton(
                                onClick = {
                                    val action = HomeNavigationDirections.actionGlobalSettingsActivity(
                                        null, Settings.MenuTag.SECTION_INPUT
                                    )
                                    navController.navigate(action)
                                }, modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Gamepad, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Controles")
                            }
                        }
                        CardBlock {
                            Text("Drivers gráficos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(
                                "Instale, selecione ou remova drivers usando o gerenciador nativo do Eden. A disponibilidade depende da GPU e do Android.",
                                color = Muted
                            )
                            Button(
                                onClick = {
                                    val action = HomeNavigationDirections.actionGlobalSettingsSubscreenActivity(
                                        SettingsSubscreen.DRIVER_MANAGER, null
                                    )
                                    navController.navigate(action)
                                }, modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Memory, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Gerenciar drivers")
                            }
                            OutlinedButton(
                                onClick = {
                                    val action = HomeNavigationDirections.actionGlobalSettingsSubscreenActivity(
                                        SettingsSubscreen.DRIVER_FETCHER, null
                                    )
                                    navController.navigate(action)
                                }, modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Download, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Buscar drivers compatíveis")
                            }
                            OutlinedButton(
                                onClick = {
                                    val action = HomeNavigationDirections.actionGlobalSettingsSubscreenActivity(
                                        SettingsSubscreen.FREEDRENO_SETTINGS, null
                                    )
                                    navController.navigate(action)
                                }, modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Tune, null)
                                Spacer(Modifier.width(8.dp))
                                Text("Opções Freedreno / Turnip")
                            }
                        }
                        CardBlock {
                            Text("Pós-processamento", fontWeight = FontWeight.Bold)
                            Text("Filtros e opções adicionais disponibilizados pelo motor.", color = Muted)
                            OutlinedButton(onClick = {
                                val action = HomeNavigationDirections.actionGlobalSettingsActivity(
                                    null, Settings.MenuTag.SECTION_POST_PROCESSING
                                )
                                navController.navigate(action)
                            }, modifier = Modifier.fillMaxWidth()) {
                                Text("Abrir opções de pós-processamento")
                            }
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
                        Text("Estado nativo: chaves ${if (keysReady) "válidas" else "ausentes/inválidas"}; firmware ${if (firmwareReady) "instalado" else "não detectado"}. Selecionar um arquivo não instala chaves nem firmware.", color = Muted, fontSize = 11.sp)
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
