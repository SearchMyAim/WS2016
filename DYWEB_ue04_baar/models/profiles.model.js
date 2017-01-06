const fs = require('fs');
const url = require('url');

const usrDir = "./profiles/";
if(!fs.existsSync(usrDir)) {
    fs.mkdirSync(usrDir);
}

const profiles = {
    newProfile: function (nickName, firstName, lastName, description, fbProfile, twProfile, xingProfile, image) {
      return {
          nickName: nickName,
          firstName: firstName,
          lastName: lastName,
          description: description,
          facebookProfile: fbProfile,
          twitterProfile: twProfile,
          xingProfile: xingProfile,
          profilePicture: image
      }
    },

    storeProfile: function (profile) {
        var pDir = usrDir + profile.nickName + "/";
        if(!fs.existsSync(pDir)) {
            fs.mkdirSync(pDir);
        }

        fs.writeFile(pDir + profile.nickName + ".json", JSON.stringify(profile), 'utf-8', (err) => {
            if (err) throw err;
        });
    },

    profileExists: function (nickName) {
        if(fs.existsSync("./profiles/" + nickName + "/" + nickName + ".json")) {
            return true;
        }
        else {
            return false;
        }
    },

    getProfile: function (nickName) {
        return JSON.parse(fs.readFileSync("./profiles/" + nickName + "/" + nickName + ".json", 'utf-8'));
    },

    storeProfileImage: function (files, nickName) {
        var dir = "./profiles/" + nickName + "/";
        if(!fs.existsSync(dir)) {
            fs.mkdirSync(dir);
        }

        const fileName = files.imagefile.name;
        const currentPath = files.imagefile.path;
        var imgName = "image" + files.imagefile.name.substr(fileName.lastIndexOf("."));
        fs.renameSync(currentPath, dir + "image" + files.imagefile.name.substr(fileName.lastIndexOf(".")));
        return imgName;
    },

    getProfileImage: function(reqUrl) {
        return fs.readFileSync("." + reqUrl, '');
    }
};

module.exports = profiles;