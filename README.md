<div align="center">

# ⚡ ARCEUS

**Um projeto dedicado de emulação para Android focado inteiramente em Pokémon Legends: Arceus.**

<p align="center">
  <img src="https://img.shields.io/badge/Plataforma-Android-3DDC84?style=flat-square&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Linguagem-Kotlin%20%7C%20C%2B%2B-blue?style=flat-square" />
  <img src="https://img.shields.io/badge/Build-Gradle-02303A?style=flat-square&logo=gradle" />
  <img src="https://img.shields.io/badge/Status-Em%20Desenvolvimento-orange?style=flat-square" />
</p>

</div>

---

## 📖 Sobre o Projeto

O **ARCEUS** é um projeto de emulação experimental derivado do código-fonte do emulador **Eden**, desenvolvido com um objetivo claro: proporcionar uma experiência focada e otimizada exclusivamente para rodar **Pokémon Legends: Arceus** no Android.

Diferente de emuladores tradicionais projetados como bibliotecas gerais de jogos, o ARCEUS busca simplificar a interface, remover complexidades desnecessárias e introduzir melhorias de usabilidade e desempenho voltadas especificamente para dispositivos móveis.

---

## 🎯 Objetivos

- **Foco Único:** Criar uma experiência Android dedicada e refinada para *Pokémon Legends: Arceus*.
- **Simplificação:** Tornar o processo de seleção e inicialização de jogos muito mais direto e prático.
- **Núcleo Robusto:** Preservar o núcleo de emulação original e suas funcionalidades essenciais herdadas do upstream.
- **Desempenho Gráfico:** Manter opções úteis de gráficos e performance, incluindo suporte integrado ao *Lossless Scaling*.
- **Interface Otimizada:** Melhorar a navegação e a experiência do usuário em telas sensíveis ao toque.
- **Transparência:** Manter um processo de desenvolvimento aberto e colaborativo.

---

## 🛠️ Tecnologias Utilizadas

| Componente | Tecnologia |
| :--- | :--- |
| **Aplicativo Android** | Kotlin |
| **Núcleo de Emulação Nativo** | C++ |
| **Interface Gráfica** | Framework UI Nativo do Android |
| **Sistema de Build** | Gradle |
| **Compilação Nativa** | CMake |
| **Automação & CI/CD** | GitHub Actions |

---

## 📊 Status do Desenvolvimento

> **⚠️ Nota:** O ARCEUS encontra-se em estágio **experimental**. O status abaixo descreve o progresso atual da aplicação, não garantindo que o jogo rode com velocidade máxima em todos os dispositivos.

| Funcionalidade | Status |
| :--- | :---: |
| Build do aplicativo Android | 🟢 Funcionando |
| Geração de APK ARM64 | 🟢 Funcionando |
| Núcleo de emulação original | 🔵 Preservado do upstream |
| Suporte ao Lossless Scaling | 🔵 Presente no código base |
| Interface dedicada ao jogo | 🟡 Em desenvolvimento |
| Inicialização simplificada direta | ⏳ Planejado |
| Manipulação de arquivos e pastas | 🟡 Em aprimoramento |
| Testes de compatibilidade e performance | 🔄 Contínuo |

---

## 📥 Instalação

As builds de desenvolvimento mais recentes são disponibilizadas automaticamente através dos artefatos do **GitHub Actions**.

1. Acesse a aba **Actions** do repositório.
2. Selecione o workflow de build para Android.
3. Clique em uma execução de build bem-sucedida (`success`).
4. Role até a seção de artefatos e baixe o APK gerado.
5. Extraia o arquivo (se necessário) e instale o APK em um dispositivo Android compatível.

> 💡 **Aviso Legal:** Utilize apenas arquivos de jogos e arquivos de sistema que você possua legalmente. O ARCEUS **não** distribui ROMs de *Pokémon Legends: Arceus*, chaves de criptografia (keys), firmware ou qualquer conteúdo protegido por direitos autorais.

---

## 📱 Requisitos de Hardware e Compatibilidade

A compatibilidade e o desempenho dependem diretamente de fatores como:
- Especificações do dispositivo Android (SoC e arquitetura).
- Suporte e otimização dos drivers de GPU.
- Memória RAM disponível.
- Configurações aplicadas no emulador e versão do jogo.

Uma compilação de APK bem-sucedida **não** garante jogabilidade fluida ou total funcionalidade. Os testes de compatibilidade continuam ativamente em andamento.

---

## 🤝 Créditos e Agradecimentos

O ARCEUS é um projeto independente que herda código e componentes do projeto **Eden Emulator**. 

Agradecemos imensamente aos desenvolvedores e contribuidores do Eden e de todos os projetos open-source dos quais ele deriva. Os avisos de direitos autorais originais, cabeçalhos de licença e atribuições de terceiros são rigorosamente mantidos no código-fonte.

---

## 📜 Isenção de Responsabilidade (Disclaimer)

O ARCEUS é um projeto independente desenvolvido pela comunidade. **Não** é afiliado, endossado, patrocinado ou associado de forma alguma à **Nintendo**, **The Pokémon Company**, **Game Freak** ou aos desenvolvedores do **Eden Emulator**.

Todas as marcas registradas e propriedade intelectual pertencem aos seus respectivos proprietários.

---

## 📄 Licença

Este projeto herda as obrigações de licenciamento de seu código-fonte upstream. Consulte o arquivo `LICENSE` no repositório e os cabeçalhos individuais dos arquivos de código para verificar os termos de licença aplicáveis.

---

<p align="center">
  <strong>ARCEUS — Um jogo. Uma experiência focada.</strong>
</p>
