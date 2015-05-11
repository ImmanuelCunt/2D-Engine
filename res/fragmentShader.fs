uniform vec2 lightLocation;
uniform vec3 lightColor;
uniform float radius;
uniform float screenHeight;

void main() {
	float distance = length(lightLocation - gl_FragCoord.xy);
	
	float attenuation = 1.0 / distance;				// - 1.0 / radius;
	
	vec4 color = vec4(attenuation, attenuation, attenuation, pow(attenuation, 10)) * vec4(lightColor, 1);

	gl_FragColor = color;
}