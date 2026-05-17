# Goal

Create a `/prompts` directory in the repository with one `.md` file per AI interaction, each following a fixed documentation structure.

# Context provided

- Example structure for each file: Goal, Context provided, Prompt, Output evaluation, Iteration notes (illustrated with a Selenium/Maven/TestNG/POM scenario as a format reference).
- Existing repo: Maven `insider-assignment` module, JUnit 5, no UI test framework yet.
- Prior session had requested similar documentation but those files were not present in the workspace.

# Prompt

> repoda şöyle bir klasör oluştur: "/prompts". İçine her AI etkileşimi için bir .md dosyası koy. Her prompt dosyasının içeriği bu mantıkta olacak;
> # Goal
> ...
> # Context provided
> ...
> # Prompt
> ...
> # Output evaluation
> ...
> # Iteration notes
> ...

# Output evaluation

Accepted: `/prompts` with numbered files per real AI interaction and the five-section layout. Rejected: copying the Selenium example as if that work had already been done, or adding prompt files for interactions with no record (e.g. undocumented Maven init).

# Iteration notes

Next iterations should add new numbered files when starting Selenium/TestNG setup, Page Object Model structure, and Insider careers-site scenarios—each with URLs, locators, and test output attached in Context provided.
