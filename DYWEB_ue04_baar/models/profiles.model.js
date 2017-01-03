const profiles = {
    // Call this to get a new profile object
    newProfile: function (nickName, firstName, lastName, description, fbProfile, twProfile, xingProfile) {
      return {
          nickName: nickName,
          firstName: firstName,
          lastName: lastName,
          description: description,
          facebookProfile: fbProfile,
          twitterProfile: twProfile,
          xingProfile: xingProfile
      }
    },

    // Persists a new profile, expects a _profile_ object as produced by function _newProfile_
    storeProfile: function (profile) {
        // TODO Store the profile as JSON in the file system as described in exercise 3
    },

    profileExists: function (nickName) {
        // TODO though optional, function might come handy for the controller to respond appropriately when there's no such profile
    },

    getProfile: function (nickName) {
        // TODO If there is a profile with that name, read it from the fileSystem and return it as an object
    }
};

module.exports = profiles;