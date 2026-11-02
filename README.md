<div align="center">

<img src="leafeon.png" alt="Leafeon — mascote visual do ARCEUS" width="180" />

# ⚡ ARCEUS

### Pokémon Legends: Arceus — Android project

**Um projeto experimental de emulação para Android, dedicado a uma experiência focada em Pokémon Legends: Arceus.**

<p>
  <img src="https://img.shields.io/badge/Plataforma-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Kotlin%20%7C%20C%2B%2B-Linguagens-7F52FF?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Build-Gradle-02303A?style=for-the-badge&logo=gradle" />
  <img src="https://img.shields.io/badge/Status-Experimental-orange?style=for-the-badge" />
</p>

[**Código-fonte**](https://github.com/srnanoki/Legends-arceus-port) ·
[**Issues**](https://github.com/srnanoki/Legends-arceus-port/issues) ·
[**Actions / Builds**](https://github.com/srnanoki/Legends-arceus-port/actions)

</div>

---

## 🌿 Sobre o projeto

O **ARCEUS** é um projeto independente baseado no código-fonte do **Eden Emulator**, com foco em uma experiência Android dedicada a *Pokémon Legends: Arceus*.

A proposta é adaptar a interface e os fluxos do aplicativo para tornar a configuração, a organização dos arquivos e o acesso ao jogo mais diretos em dispositivos móveis — preservando o núcleo de emulação e os componentes nativos herdados do projeto upstream.

> **Importante:** o ARCEUS ainda está em desenvolvimento experimental. Uma compilação bem-sucedida não significa, por si só, que o jogo esteja totalmente funcional ou tenha desempenho ideal em todos os aparelhos.

## ✨ Objetivos

- 🎮 **Experiência dedicada:** interface e navegação orientadas a Pokémon Legends: Arceus.
- 📱 **Usabilidade móvel:** controles e telas pensados para Android.
- 🧩 **Núcleo preservado:** manter o motor de emulação e os componentes essenciais do Eden.
- ⚙️ **Configuração real:** conectar as opções da interface às configurações efetivamente suportadas pelo emulador.
- 🚀 **Desempenho:** facilitar o acesso às opções gráficas e de compatibilidade disponíveis no dispositivo.
- 🔍 **Transparência:** distinguir recursos implementados, em teste e planejados.

## 📊 Estado do desenvolvimento

Os estados abaixo representam o andamento conhecido do projeto; não são uma garantia de compatibilidade ou desempenho.

| Área | Estado |
|---|:---:|
| Projeto Android e sistema de build | 🟢 Estrutura existente |
| Build Android via GitHub Actions | 🟢 Build 0.0.3 reportada como concluída |
| Núcleo de emulação herdado do Eden | 🔵 Preservado no código-base |
| Interface dedicada ARCEUS | 🟡 Em desenvolvimento |
| Navegação e gerenciamento de arquivos | 🟡 Em aprimoramento |
| Configurações gráficas integradas ao motor | 🟡 Em integração e validação |
| Importação e validação de firmware/keys | 🟡 A validar no fluxo dedicado |
| Inicialização do jogo no dispositivo | 🧪 Testes necessários |
| Compatibilidade e desempenho por aparelho | 🔄 Testes contínuos |

**Legenda:** 🟢 disponível/confirmado · 🔵 herdado do projeto-base · 🟡 em desenvolvimento · 🧪 precisa de validação · 🔄 trabalho contínuo.

## 🛠️ Tecnologias

| Componente | Tecnologia |
|---|---|
| Aplicativo Android | Kotlin |
| Núcleo nativo | C++ |
| Interface | Android / Jetpack Compose e componentes do projeto-base |
| Build | Gradle |
| Compilação nativa | CMake |
| Automação | GitHub Actions |

## 📲 Instalação de builds de desenvolvimento

As builds automatizadas, quando disponíveis, podem ser consultadas na aba **Actions**:

1. Abra [GitHub Actions](https://github.com/srnanoki/Legends-arceus-port/actions).
2. Selecione o workflow de compilação Android.
3. Abra uma execução concluída com sucesso.
4. Na seção **Artifacts**, baixe o APK disponibilizado.
5. Instale o APK em um dispositivo Android compatível. Talvez seja necessário autorizar a instalação de aplicativos dessa origem nas configurações do Android.

> Baixe somente artefatos publicados pelo repositório oficial e confira se a execução terminou com sucesso.

## 📦 Firmware, chaves e jogos

O ARCEUS não inclui firmware, chaves criptográficas ou cópias de jogos.

- Use somente arquivos obtidos legalmente e que você tenha direito de utilizar.
- Importe os arquivos pelo próprio aplicativo, quando os respectivos fluxos estiverem disponíveis.
- Não publique firmware, `prod.keys`, `title.keys`, jogos, saves ou outros arquivos pessoais no GitHub.
- A disponibilidade de um seletor de arquivos não significa, por si só, que a instalação ou validação tenha sido concluída.

## ⚙️ Gráficos, drivers e desempenho

O desempenho depende de diversos fatores, incluindo chipset, GPU, driver gráfico, memória disponível, versão do Android e estado de compatibilidade do jogo.

As opções de resolução, anti-aliasing, backend/renderizador e drivers devem ser consideradas funcionais somente quando estiverem conectadas ao motor de emulação e tiverem sido testadas. A presença de uma opção na interface não é prova de que ela esteja sendo aplicada.

Drivers gráficos externos ou alternativos devem ser usados apenas quando forem compatíveis com o dispositivo e com a versão do emulador. Uma configuração inadequada pode causar falhas, artefatos visuais ou encerramentos inesperados.

## 📱 Compatibilidade

Não existe garantia de que todos os dispositivos Android consigam executar o jogo. A compatibilidade pode variar conforme:

- SoC e arquitetura do processador;
- GPU e versão do driver;
- memória RAM disponível;
- versão do Android;
- versão do jogo e arquivos de sistema utilizados;
- configurações do emulador.

Relatos de testes são bem-vindos. Ao informar um problema, inclua o modelo do aparelho, versão do Android, versão/build do ARCEUS e um log relevante — **sem anexar keys, firmware, jogos ou dados pessoais**.

## 🧑‍💻 Desenvolvimento

O projeto utiliza Gradle para o aplicativo Android e CMake para partes nativas. O workflow de CI fica em:

```text
.github/workflows/android-build.yml
