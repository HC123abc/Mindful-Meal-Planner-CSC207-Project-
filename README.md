**API KEY NOTE**
 The current code includes preconfigured APIs for simplicity during testing and code execution. 
 You can get your own API Key from Spoonacular by visiting https://spoonacular.com/food-api and signing up a free account to get a free API key. Once you've obtained your unique API key, you can easily replace the predefined APIs in the GenerateRecipeInteractor.java and FavViewInteractor.java files.
 
 **Problem Domain**

Have you ever found yourself wondering what to cook, or wanting to try something new but needing more confidence? Our team wants to create a practical and user-friendly application called Recipe Generator (not official name). With the growing trend of home cooking and the increasing importance of balanced nutrition, we aim to assist home chefs by offering a seamless way to discover recipes that perfectly match their dietary preferences, restrictions, and health goals. With just a simple click users can create an exciting dish every day. Additionally, we do offer nutritional information, including calorie counts. However, this data is not the central focus of the application. Our goal is to give users creative ideas and encourage users to try new cooking recipes. This will also save time as our application will remove the tedious task of surfing the web to find a desired recipe.

**Overview**

Our envisioned application is a user-friendly recipe generator. Users will begin by creating an account, signing in, or linking their Google account (if this is possible).

Login:

- For new users, the app will prompt them to specify their food preferences, including preferred cuisines (such as Chinese, Italian, etc.), dietary goals (weight loss, weight gain, or don’t care), any allergies or dietary restrictions they may have, and good cooker or bad cooker.
- Existing users will be directed straight to the main page, where their previously set preferences will be loaded. Users will have the option to modify their preferences at any time through the settings.

Main page:

- The application will have a straightforward graphical user interface (GUI). It will show two prominent buttons: one for generating a random recipe and another for accessing their saved favorites. The favorite recipes will be categorized into breakfast, lunch, and dinner sections for easy navigation.
- Other buttons includes Shopping which will send the user to a page where we organize and compare prices of ingredients of 4 (maybe more) supermarkets, and a setting button to change preferences, log out ,etc.
- There will be a daily calorie count: calorie intake (information from the recipe generated)\ total daily calories

Generating recipe page:

- Upon clicking the "Generate Recipe" button, the application will retrieve the current time from the user's device using a relevant Java package (have to research this). This information will help in distinguishing for breakfast, lunch, or dinner. The application will then make an API call to either ChatGPT or a website with recipes to fetch a recipe. Then retrieved recipe will be shown to the user by a concise description of the dish, calories, and hopefully, an image for visual reference (if this is easy to do).
- Users will have the choice to either proceed with cooking the suggested recipe by clicking the "Cook This" button, or they can opt to re-roll for a different suggestion. If they choose to proceed, they will be directed to the detailed recipe page. Here, they will find a list of required ingredients and step-by-step instructions. A star button will be positioned in the bottom right corner, allowing users to save recipes in their favorites collection for future reference.
