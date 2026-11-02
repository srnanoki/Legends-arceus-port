::: {align="center"}
`<img src="leafeon.png" alt="Leafeon — mascote visual do ARCEUS" width="180" />`{=html}

# ⚡ ARCEUS

### Pokémon Legends: Arceus --- Android project

**Um projeto experimental de emulação para Android, dedicado a uma
experiência focada em Pokémon Legends: Arceus.**

```{=html}
<p>
```
`<img src="https://img.shields.io/badge/Plataforma-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android" />`{=html}
`<img src="https://img.shields.io/badge/Kotlin%20%7C%20C%2B%2B-Linguagens-7F52FF?style=for-the-badge" alt="Kotlin e C++" />`{=html}
`<img src="https://img.shields.io/badge/Build-Gradle-02303A?style=for-the-badge&logo=gradle" alt="Gradle" />`{=html}
`<img src="https://img.shields.io/badge/Status-Experimental-orange?style=for-the-badge" alt="Experimental" />`{=html}
```{=html}
</p>
```
[**Código-fonte**](https://github.com/srnanoki/Legends-arceus-port) ·
[**Issues**](https://github.com/srnanoki/Legends-arceus-port/issues) ·
[**Actions /
Builds**](https://github.com/srnanoki/Legends-arceus-port/actions)
:::

------------------------------------------------------------------------

## 🌿 Sobre o projeto

O **ARCEUS** é um projeto independente baseado no código-fonte do **Eden
Emulator**, com foco em uma experiência Android dedicada a *Pokémon
Legends: Arceus*.

A proposta é adaptar a interface e os fluxos do aplicativo para tornar a
configuração, a organização dos arquivos e o acesso ao jogo mais diretos
em dispositivos móveis --- preservando o núcleo de emulação e os
componentes nativos herdados do projeto upstream.

> **Importante:** o ARCEUS ainda está em desenvolvimento experimental.
> Uma compilação bem-sucedida não significa, por si só, que o jogo
> esteja totalmente funcional ou tenha desempenho ideal em todos os
> aparelhos.

## ✨ Objetivos

-   🎮 **Experiência dedicada:** interface e navegação orientadas a
    Pokémon Legends: Arceus.
-   📱 **Usabilidade móvel:** controles e telas pensados para Android.
-   🧩 **Núcleo preservado:** manter o motor de emulação e os
    componentes essenciais do Eden.
-   ⚙️ **Configuração real:** conectar as opções da interface às
    configurações efetivamente suportadas pelo emulador.
-   🚀 **Desempenho:** facilitar o acesso às opções gráficas e de
    compatibilidade disponíveis no dispositivo.
-   🔍 **Transparência:** distinguir recursos implementados, em teste e
    planejados.

## 📊 Estado do desenvolvimento

Os estados abaixo representam o andamento conhecido do projeto; não são
uma garantia de compatibilidade ou desempenho.

  -----------------------------------------------------------------------
  Área                                          Estado
  -------------------------- --------------------------------------------
  Projeto Android e sistema             🟢 Estrutura existente
  de build                   

  Build Android via GitHub     🟢 Build 0.0.3 reportada como concluída
  Actions                    

  Núcleo de emulação herdado         🔵 Preservado no código-base
  do Eden                    

  Interface dedicada ARCEUS             🟡 Em desenvolvimento

  Navegação e gerenciamento              🟡 Em aprimoramento
  de arquivos                

  Configurações gráficas             🟡 Em integração e validação
  integradas ao motor        

  Importação e validação de         🟡 A validar no fluxo dedicado
  firmware/keys              

  Inicialização do jogo no              🧪 Testes necessários
  dispositivo                

  Compatibilidade e                      🔄 Testes contínuos
  desempenho por aparelho    
  -----------------------------------------------------------------------

**Legenda:** 🟢 disponível/confirmado · 🔵 herdado do projeto-base · 🟡
em desenvolvimento · 🧪 precisa de validação · 🔄 trabalho contínuo.

## 🛠️ Tecnologias

  Componente           Tecnologia
  -------------------- ---------------------------------------------------------
  Aplicativo Android   Kotlin
  Núcleo nativo        C++
  Interface            Android / Jetpack Compose e componentes do projeto-base
  Build                Gradle
  Compilação nativa    CMake
  Automação            GitHub Actions

## 📲 Instalação de builds de desenvolvimento

As builds automatizadas, quando disponíveis, podem ser consultadas na
aba **Actions**:

1.  Abra [GitHub
    Actions](https://github.com/srnanoki/Legends-arceus-port/actions).
2.  Selecione o workflow de compilação Android.
3.  Abra uma execução concluída com sucesso.
4.  Na seção **Artifacts**, baixe o APK disponibilizado.
5.  Instale o APK em um dispositivo Android compatível. Talvez seja
    necessário autorizar a instalação de aplicativos dessa origem nas
    configurações do Android.

> Baixe somente artefatos publicados pelo repositório oficial e confira
> se a execução terminou com sucesso.

## 📦 Firmware, chaves e jogos

O ARCEUS não inclui firmware, chaves criptográficas ou cópias de jogos.

-   Use somente arquivos obtidos legalmente e que você tenha direito de
    utilizar.
-   Importe os arquivos pelo próprio aplicativo, quando os respectivos
    fluxos estiverem disponíveis.
-   Não publique firmware, `prod.keys`, `title.keys`, jogos, saves ou
    outros arquivos pessoais no GitHub.
-   A disponibilidade de um seletor de arquivos não significa, por si
    só, que a instalação ou validação tenha sido concluída.

## ⚙️ Gráficos, drivers e desempenho

O desempenho depende de diversos fatores, incluindo chipset, GPU, driver
gráfico, memória disponível, versão do Android e estado de
compatibilidade do jogo.

As opções de resolução, anti-aliasing, backend/renderizador e drivers
devem ser consideradas funcionais somente quando estiverem conectadas ao
motor de emulação e tiverem sido testadas. A presença de uma opção na
interface não é prova de que ela esteja sendo aplicada.

Drivers gráficos externos ou alternativos devem ser usados apenas quando
forem compatíveis com o dispositivo e com a versão do emulador. Uma
configuração inadequada pode causar falhas, artefatos visuais ou
encerramentos inesperados.

## 📱 Compatibilidade

Não existe garantia de que todos os dispositivos Android consigam
executar o jogo. A compatibilidade pode variar conforme:

-   SoC e arquitetura do processador;
-   GPU e versão do driver;
-   memória RAM disponível;
-   versão do Android;
-   versão do jogo e arquivos de sistema utilizados;
-   configurações do emulador.

Relatos de testes são bem-vindos. Ao informar um problema, inclua o
modelo do aparelho, versão do Android, versão/build do ARCEUS e um log
relevante --- **sem anexar keys, firmware, jogos ou dados pessoais**.

## 🧑‍💻 Desenvolvimento

O projeto utiliza Gradle para o aplicativo Android e CMake para partes
nativas. O workflow de CI fica em:

``` text
.github/workflows/android-build.yml
```

Para contribuir, abra uma
[Issue](https://github.com/srnanoki/Legends-arceus-port/issues)
descrevendo o problema ou a melhoria proposta. Antes de enviar
alterações, preserve os avisos de copyright, cabeçalhos de licença e
atribuições presentes nos arquivos originais.

## 🙏 Créditos e agradecimentos

O ARCEUS é um projeto independente que utiliza código e componentes do
**Eden Emulator** e de outros projetos de código aberto.

Todo crédito, aviso de copyright, licença e atribuição de terceiros deve
permanecer associado aos respectivos componentes. Consulte os arquivos
de licença e os cabeçalhos do código-fonte para conhecer os termos
aplicáveis.

## ⚖️ Aviso legal

ARCEUS é um projeto independente e **não é afiliado, endossado,
patrocinado ou associado** à Nintendo, The Pokémon Company, Game Freak
ou aos desenvolvedores do Eden Emulator.

Pokémon e demais marcas citadas pertencem aos seus respectivos
titulares. O projeto não distribui ROMs, jogos, firmware ou chaves
criptográficas.

## 📄 Licença

O projeto incorpora código de terceiros e herda as obrigações de
licenciamento aplicáveis a esses componentes. Consulte o arquivo
`LICENSE` e os cabeçalhos individuais dos arquivos para identificar os
termos correspondentes. Não presuma que todo o conteúdo do repositório
esteja coberto por uma única licença sem verificar suas origens.

------------------------------------------------------------------------

::: {align="center"}
### ⚡ ARCEUS

**Um jogo. Uma experiência focada.**

*Feito com dedicação pela comunidade.*
:::
