# AI usage reflection

Answer each question in your own words (2–3 sentences recommended).

---

## 1. Explain the terms context, prompt, skill, and agent as they relate to AI coding assistants.

<!-- Your answer -->

Context: The background information that allows the AI ​​to see the code, files, and error logs we are currently working on.

Prompt: The question or instruction I give the AI ​​directly, telling it what I want it to do.

Skill: The tools the AI ​​possesses besides writing code; such as running a terminal, creating files, or searching the internet.

Agent: A system that doesn't just answer a single question, but autonomously plans and executes a large task I give it.

## 2. How did you validate the AI output? Which parts did you reject and why?

<!-- Your answer -->

I verify the AI ​​output by running the test. Of course, a successful test doesn't mean it's working correctly. I check the assertions.
AI can be too cautious, so I removed redundant if and for loops because they can impose too many controls. I remove hardcoded data, static waiting, and fragile locators.

## 3. Describe one point where the AI was weak in this task and how you solved it manually.

<!-- Your answer -->

I tried to assign the locators myself and instructed them to ensure the code was stable and maintainable. There weren't any steps requiring manual correction. I resolved minor issues by communicating the solutions to the AI.

## 4. In which situations would you prefer to write code manually instead of using AI?

When AI generates static wait times or excessive steps in complex operations, I modify the code myself to make it more efficient. Especially in application automation, I refine the code myself through trial and error to optimize scrolling operations and ensure optimal performance.
