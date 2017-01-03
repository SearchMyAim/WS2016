# Dynamisches Web- UE03 - Baar Alexander
===
## Todo List and Comments
1. Wrong links
+ If the user at the primary resource does not exist (localhost:3000/user) and the '/edit' page is requested return a "Not Found / 400" error.

2. Not implemented functionality
+ Caching
+ Picture deletion; If the user uploads a new picture the old one is NOT deleted. The pictures are parsed sequentially. If the old one is a .jpg and the new one a .png the old one is returned because it has been found first.
+ Check username input for validation
    + A new user shall not be able to override a existing user profile
    + Names like "pics" are not allowed to due resources conflictation. 

3. Style
+ Given links at the profile page are still blue colored and underlined!

4. Optimization
+ Check code redundance in the html templates.