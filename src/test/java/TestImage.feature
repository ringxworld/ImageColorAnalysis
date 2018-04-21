Feature: Test Images

  @urlexists
  Scenario: Go to selected Page
    Given I go to "0qow2dksb34z"
    Then I should be on "0qow2dksb34z"

  @urlfiletype
  Scenario: Url is jpg format
  	Given I go to "0qow2dksb34z.jpg"
  	Then File extension is ".jpg" 
  
  @FileManager
  Scenario: File will create correctly
  	Given I create output file "ImageTestFile.csv"
  	Then "ImageColors.csv" file exists
  	
  @ImageAvailable
  Scenario: Image will be streamed with ImageIO.read
  	Given I have a valid "https://i.redd.it/0qow2dksb34z.jpg" that can be streamed 
  	Then "https://i.redd.it/0qow2dksb34z.jpg" bufferedimage exists
  	