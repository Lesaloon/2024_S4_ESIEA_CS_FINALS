# Business Requirementss

This is a tutorial app
a tutorial has :
- a name / title
- a type ("cooking", "DIY", "arts and crafts") or ( "recette de cuisine", "bricolage","Loisir créatif")
- a description
- a list of tools needed
- a list of materials needed or ingredients
- a photo
- a time to complete (BONUS)
- a cost (BONUS)
- a difficulty level (easy, medium, hard) (BONUS)
- Multiple steps

The tool list is not a list of individual tools, its an object of its own :
- a name
- a description
- a photo

The list of steps is an actual list.
Each step has :
- a title
- a sequence number
- a description (BONUS)
- a photo


# Front end requirements

## Home page
- navbar with :
  - name of the app
  - a filter by type (dropdown)
  - a way to change the view (list or grid)
  - a search bar (BONUS)
  - a button to add a new tutorial
- a way to display all the tutorials
- a way to edit a tutorial
- a way to create a new tutorial

## Tutorial page
- a way to display the tutorial
- a way to edit the tutorial
- a way to delete the tutorial
- a way to go back to the home page
- a way to go to the next step


## Tutorial creation page
- a way to create a new tutorial
- a way to add a new step
- a way to delete a step
- a way to move a step up or down

## Tutorial edition page
- a way to edit the tutorial
- a way to edit a step
- a way to delete a step
- a way to move a step up or down
- a way to add a new step
- a way to delete the tutorial ( confirmation required )
- a way to go back to the tutorial page

## User actions

## text editor
to edit any text that is not a title or a name we need a WYSIWYG editor

## Image upload (BONUS)
to upload images we need a way to select a file from the computer or by URL

## Lazy loading
only load the tutorials that are visible on the screen

## Step numbering
the steps should be numbered automatically

## Form validation
must not be able to submit a form with missing required fields

# Back end requirements

## Logging
every action should be logged withe the level INFO in a file called "TutoAPI_xx.log" where xx is a sequential number. new log file should be created every 100Ko

Every exit of the API should be logged with the following format depending on the status code :
- INFO: if status is OK (200) or CREATED (201)
- WARNING: external error (4xx)
- ERROR: internal error (5xx)

every printStackTrace of the Exception no matter where it is should be logged with the level ERROR

if an error occurs outside of the service layer, the error should be caught and logged with the level ERROR in a file called "TutoErrors.log"

By default, log anything with the level INFO or higher

## Unit tests
every service should have a unit test that tests every method using a RAM database (hsqldb)

api coverage should be at least 85%

## Persistence

MySQL database using JPA 2.1 with EclipseLink 2.5.2
models will be managed by a or multiple DAOs that will be accessed by a DAOFactory singleton
