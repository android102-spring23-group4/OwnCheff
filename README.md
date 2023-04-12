Milestone 1 - OwnCheff

## Table of Contents

1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Wireframes](#Wireframes)

## Overview

### Description

Will give a new recipe everyday to make at home. Uses the TheMealDB/Zestful API, zestful will allow the user to make a decision for alternative ingredients so the user doesn't have to buy a bunch of ingredients that they would use for only 1 recipe. Would allow users to swipe for recipes they liked and disliked and then would cater to the users likes for future recipes.

### App Evaluation

[Evaluation of your app across the following attributes]

- **Category:** Lifestyle
- **Mobile:** Mobile is essential for the convenience of users. Having a small mobile device while cooking will free up space for users when they are cooking. Users will also be able to see the ingredients needed on the fly enabling the user to know which ingredients to purchase on the spot.
- **Story:** Anybody who loves cooking will relish the challenge of making a new recipe everyday. Never be tired of cooking with cuisines from all over the world. 
- **Market:** Anybody who wants to cook can be a target audience. 
- **Habit:** If the reccomended recipes are good enough people will check it daily like they do social media. Then they will develop a habit of checking it everyday. 
- **Scope:** V1 would merely show a new random recipe everyday. V2 would allow for users to comment and show off thier food to the world. V3 would allow for streaks when people try for more and more days in a row. V4 would allow users to follow other users and see what new food everyone is cooking in a feed.

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

- [ ] User is able to create an account
- [ ] User can log in and log out
- [ ] User is able to get new recipes everyday
- [ ] User is able to see a picture of the recipe
- [ ] User can like a recipe
- [ ] User can see a list of liked recipes
- [ ] User can see a list of cooked recipes

**Stretch Features**

- [ ] User is able to swipe to either like or dislike a recipe
- [ ] User is able to input ingredients to get recipes including inputted ingredients
- [ ] User is able to rate the recipes 
- [ ] User is able to choose dietary options such as pescatarian, vegan, keto etc...

### 2. Screen Archetypes

- Login Screen
  - User should be able to input an username and password.
  - User should be able to register 
- Register Screen (Could be fragment)
    - Register Screen should have an input for username and password for which the user can use to create an account.
- Recipe screen
  - Screen where user can see the recipe name and ingredients.
  - Instructions will be also visible on this screen.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Recipe tab with directions
* Ingredients tab
* Profile tab

**Flow Navigation** (Screen to Screen)

- Login Activity
  - Default starting activity on app launch
  - Login Activity -> Register if register is an activity or if its fragment do not need.
  - Login Activity -> Main Activity
- Main Activity
  - Main Activity contains tabs which are fragments
  - Main activity contains the recipe fragment, ingredient fragment, and profile fragment
  - Main Activity -> Login Activity on logout

## Wireframes

![image (3)](https://user-images.githubusercontent.com/37948407/230943125-080f8252-0c82-4077-90e7-484b80cc9342.png)


### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

![OwnChefPrototypeDemo](https://user-images.githubusercontent.com/37948407/230943261-a76ba43c-2852-4e91-9e52-606d5df53407.gif)

