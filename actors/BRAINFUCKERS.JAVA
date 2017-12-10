damagetype FireExtDamage {
	Factor 1
	ReplaceFactor
}

ACTOR FireExtPuff
{
  Height 5
  Radius 5
  +FORCEXYBILLBOARD
  RenderStyle Add
  Alpha 0.85
  Projectile
  Speed 30
  Scale 0.5
  Damage 1
  DamageType FireExtDamage
  Gravity 10
  SeeSound "weapons/plasmax"
  DeathSound "weapons/plasmax"
  States
  {
  Spawn:
    STEM A 1 A_SetTranslucent(0.6, 1)
    STEM B 1 A_SetTranslucent(0.55, 1)
    STEM C 1 A_SetTranslucent(0.5, 1)
    STEM D 1 A_SetTranslucent(0.45, 1)
    STEM E 1 A_SetTranslucent(0.4, 1)
    STEM F 1 A_SetTranslucent(0.35, 1)
    STEM G 1 A_SetTranslucent(0.3, 1)
    STEM H 1 A_SetTranslucent(0.25, 1)
    STEM I 1 A_SetTranslucent(0.2, 1)
    STEM J 1 A_SetTranslucent(0.15, 1)
    STEM K 1 A_SetTranslucent(0.1, 1)
    STEM L 1 A_SetTranslucent(0.05, 1)
  Death:
    STEM L 0
    Stop
  }
}
actor FireExtPuffNoDamage : FireExtPuff {
	Damage 0
}