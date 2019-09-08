package com.thoughtworks.geeknight.model

data class ParticipantProfile(val linkedInProfile: LinkedInProfile,val githubProfile:GithubProfile)

data class GithubProfile (val projects:List<String>,val languages:List<String>)
data class LinkedInProfile(val experience:Double,val companies:Int,val location:String)
