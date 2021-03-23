Feature: Verify Twitter Login Functionality

  @TwitterTest
  Scenario: LoginTest - Verify Login into Twitter Account
   Given user navigates to the Twitter URL
   And user validates mandatory fields for login of twitter
   And user logs in into twitter
   And user uploads profile photo
   And user gets times of india tweets of past 2 hours


    #DB Queries
#2.1 Orphans in first table
#  select * from products p
#  left join product_categories pc on p.id=pc.product_id
#  where p.product_id is null

#2.2 Orphans in second table
#  select * from products p
#  left join product_categories pc on p.id=pc.product_id
#  where pc.product_id is null









